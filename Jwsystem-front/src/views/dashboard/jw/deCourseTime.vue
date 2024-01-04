<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'

  require('echarts/theme/macarons') // echarts theme
  import {debounce} from '@/utils'

  const animationDuration = 6000

  export default {
    props: {
      data: {
        type: Array,
        required: false
      },
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
        default: '270px'
      }
    },
    data() {
      return {
        chart: null
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
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c}人 ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left:   0,
            data: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日']
          },
          grid:{
            left: '20%'
          },
          series: [
            {
              name: '旷课人数',
              type: 'pie',
              radius: ['50%', '70%'],
              avoidLabelOverlap: false,
              data: [
                {value: this.data[0], name: '星期一'},
                {value: this.data[1], name: '星期二'},
                {value: this.data[2], name: '星期三'},
                {value: this.data[3], name: '星期四'},
                {value: this.data[4], name: '星期五'},
                {value: this.data[5], name: '星期六'},
                {value: this.data[6], name: '星期日'},
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        })
      }
    }
  }
</script>
