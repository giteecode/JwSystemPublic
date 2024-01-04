<template>
  <div :class="className" :style="{ height: height, width: width }" id="heap" />
</template>

<script>
import echarts from "echarts";

require("echarts/theme/macarons"); // echarts theme
import { debounce } from "@/utils";
import service from "../../utils/request";

const animationDuration = 6000;

export default {
  props: {
    className: {
      type: String,
      default: "chart"
    },
    width: {
      type: String,
      default: "100%"
    },
    height: {
      type: String,
      default: "300px"
    }
  },
  data() {
    return {
      chart: null,
      dataHeap: [],
      dataNonHeap: [],
      time: [],
      i: 0
    };
  },
  mounted() {
    this.initChart();
    this.__resizeHandler = debounce(() => {
      if (this.chart) {
        this.chart.resize();
      }
    }, 100);
    window.addEventListener("resize", this.__resizeHandler);
    this.getEchartData();
    //setInterval(this.getEchartData, 2000)
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    window.removeEventListener("resize", this.__resizeHandler);
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, "macarons");

      this.chart.setOption({
        color: ["#FFE479", "#4DB0F1"],
        tooltip: {
          show: true,
          trigger: "axis", // 触发类型
          showContent: false
        },
        grid: {
          left: 50,
          right: 35,
          bottom: 25
        },
        legend: {
          left: 100,
          top: 20,
          align: "right",
          itemGap: 150,
          itemWidth: 10,
          data: [
            {
              name: "SIZE",
              icon: "circle"
            },
            {
              name: "USED",
              icon: "circle"
            }
          ]
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            axisLabel: {
              interval: 0,
              color: ["#666"]
            },
            axisLine: {
              lineStyle: {
                color: "#4997c5"
              }
            },
            axisTick: {
              show: false
            },
            data: []
          }
        ],
        yAxis: {
          type: "value",
          axisLabel: {
            interval: 0,
            color: ["#666"]
          },
          axisLine: {
            lineStyle: {
              color: "#4997c5"
            }
          },
          axisTick: {
            show: false
          },
          splitArea: {
            show: true,
            areaStyle: {
              color: ["#ecfafe", "#fff"]
            }
          },
          splitLine: {
            lineStyle: {
              color: ["#d8f2f9"]
            }
          }
        },
        series: [
          {
            name: "SIZE",
            type: "line",
            symbolSize: 0,
            areaStyle: {
              normal: {
                type: "default",
                color: "#FFE479",
                opacity: 1
              }
            },
            smooth: true,
            lineStyle: {
              color: "#FFE479",
              width: 0
            },
            symbol: "pin",
            symbolSize: 91,
            symbolOffset: [0, 0],
            showSymbol: false,
            smooth: true,
            label: {
              show: true,
              color: "#fff",
              formatter: "size\n{c}MB",
              position: "top",
              distance: -40
            },
            data: []
          },
          {
            name: "USED",
            type: "line",
            symbolSize: 0,
            areaStyle: {
              normal: {
                type: "default",
                color: "#4DB0F1",
                opacity: 1
              }
            },
            symbol: "pin",
            symbolSize: 90,
            symbolOffset: [0, 0],
            showSymbol: false,
            smooth: true,
            lineStyle: {
              color: "#fff",
              width: 0
            },
            label: {
              show: true,
              color: "#fff",
              formatter: "used\n{c}MB",
              position: "top",
              distance: -40
            },
            data: []
          }
        ]
      });
    },
    getEchartData() {
      service.get("/api/main/data").then(res => {
        let d = new Date();
        // let time4 = date(0)
        if (this.i > 3) {
          this.dataHeap.shift();
          this.dataNonHeap.shift();
          this.time.shift();
          this.i--;
        }
        this.dataHeap.push(res.UsedMemory);
        this.dataNonHeap.push(res.totalMemory);
        this.time.push(this.dateTransfer(0));
        this.i++;
        // this.chart = echarts.init(document.getElementById("heap"), 'macarons')
        this.chart.setOption({
          xAxis: {
            data: this.time
          },
          series: [
            {
              // 根据名字对应到相应的系列
              name: "SIZE",
              data: this.dataNonHeap
            },
            {
              // 根据名字对应到相应的系列
              name: "USED",
              data: this.dataHeap
            }
          ]
        });
      });
    },
    dateTransfer(mill) {
      let d = new Date();
      let h = d.getHours();
      let m = d.getMinutes();
      let s = d.getSeconds();
      if (s - mill < 0) {
        s = 60 - mill;
        if (m - 1 < 0) {
          m = 60 - m;
          if (h - 1 < 0) {
            h = 12 - 1;
          } else if (h - 1 >= 0 && h - 1 < 10) {
            h = "0" + h;
          } else {
            h = h - 1;
          }
        } else if (m - 1 >= 0 && m - 1 < 10) {
          m = "0" + m;
        } else {
          m = m - 1;
        }
      } else if (s - mill < 10 && mill >= 0) {
        s = "0" + s;
      }
      return h + ":" + m + ":" + s;
    }
  }
};
</script>
