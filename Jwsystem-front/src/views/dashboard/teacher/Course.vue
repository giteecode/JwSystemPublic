<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons') // echarts theme
  import { debounce } from '@/utils'
  import service from '../../../utils/request'

  const animationDuration = 6000

  export default {
    props: {
      className: {
        type: String,
        default: 'chart'
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
        data:[{
          name: 'Apples',
          value: 70
        }, {
          name: 'Strawberries',
          value: 68
        }, {
          name: 'Bananas',
          value: 48
        }, {
          name: 'Oranges',
          value: 40
        }, {
          name: 'Pears',
          value: 32
        }, {
          name: 'Pineapples',
          value: 27
        }, {
          name: 'Grapes',
          value: 18
        }]
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
            text: 'Customized Pie',
            left: 'center',
            top: 20,
            textStyle: {
              color: '#ccc'
            }
          },

          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },

          visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
              colorLightness: [0, 1]
            }
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: '55%',
              center: ['50%', '50%'],
              data: [
                {value: 335, name: '直接访问'},
                {value: 310, name: '邮件营销'},
                {value: 274, name: '联盟广告'},
                {value: 235, name: '视频广告'},
                {value: 400, name: '搜索引擎'}
              ].sort(function (a, b) { return a.value - b.value; }),
              roseType: 'radius',
              label: {
                color: 'rgba(255, 255, 255, 0.3)'
              },
              labelLine: {
                lineStyle: {
                  color: 'rgba(255, 255, 255, 0.3)'
                },
                smooth: 0.2,
                length: 10,
                length2: 20
              },
              itemStyle: {
                color: '#c23531',
                shadowBlur: 200,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              },

              animationType: 'scale',
              animationEasing: 'elasticOut',
              animationDelay: function (idx) {
                return Math.random() * 200;
              }
            }
          ]
        })
      }
    }
  }
</script>
