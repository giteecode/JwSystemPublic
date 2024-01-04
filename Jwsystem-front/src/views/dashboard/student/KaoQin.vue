<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons') // echarts theme
  import {debounce} from '@/utils'
  import service from '../../../utils/request'

  const animationDuration = 6000

  export default {
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      data: {
        type: Array,
        required: false
      },
      width: {
        type: String,
        default: '100%'
      },
      height: {
        type: String,
        default: '300px'
      }
    },
    data() {
      return {
        chart: null,
        trueNum: null,
        total: null,
        falseNum: null,
      }
    },
    mounted() {
      this.initChart()
      this.__resizeHandler = debounce(() => {
        if (this.chart) {
          this.chart.resize()
        }
      }, 100)
      window.addEventListener('resize', this.__resizeHandler)
      /*  service.get('/api/count/productIdentify').then(res => {
        this.trueNum = res.trueNum
        this.total = res.total
        this.falseNum = this.total - this.trueNum
        this.chart.setOption({
          series: [{
            data: [{ value: (this.trueNum / this.total * 100).toFixed(2), name: '真假占比' }]
          }]
        })
      })*/
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      window.removeEventListener('resize', this.__resizeHandler)
      this.chart.dispose()
      this.chart = null
    },
    methods: {
      initChart() {
        this.chart = echarts.init(this.$el, 'macarons')
        this.chart.setOption({
          title: {
            text: "本周考勤情况",
            left: '50%',
            top: '10',
            textStyle: {
              color: 'black',
              fontFamily: 'Courier New'
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
              type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          legend: {
            data: ['1-2节', '3-4节', '5-6节', '7-8节', '9-10节'],
            orient: 'vertical',
            left: 10,
            top: 50
          },
          grid: {
            left: '13%',
            bottom: '3%',
            right: 0,
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [
            {
              name: '1-2节',
              type: 'bar',
              data: [this.data[0][0], this.data[1][0], this.data[2][0], this.data[3][0], this.data[4][0], this.data[5][0], this.data[6][0]]
            },
            {
              name: '3-4节',
              type: 'bar',
              data: [this.data[0][1], this.data[1][1], this.data[2][1], this.data[3][1], this.data[4][1], this.data[5][1], this.data[6][1]]
            },
            {
              name: '5-6节',
              type: 'bar',
              data: [this.data[0][2], this.data[1][2], this.data[2][2], this.data[3][2], this.data[4][2], this.data[5][2], this.data[6][2]]
            },
            {
              name: '7-8节',
              type: 'bar',
              data: [this.data[0][3], this.data[1][3], this.data[2][3], this.data[3][3], this.data[4][3], this.data[5][3], this.data[6][3]]
            },
            {
              name: '9-10节',
              type: 'bar',
              data: [this.data[0][4], this.data[1][4], this.data[2][4], this.data[3][4], this.data[4][4], this.data[5][4], this.data[6][4]],
            }
          ]
        })
      }
    }
  }
</script>
