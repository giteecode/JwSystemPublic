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
      gonggong: {
        type: Array,
        required: false
      },
      xueke: {
        type: Array,
        required: false
      },
      zhuanye: {
        type: Array,
        required: false
      },
      tongshi: {
        type: Array,
        required: false
      },
      shijian: {
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
            text: '成绩能力模型',
            textStyle: {
              color: 'black',
              fontFamily: 'Courier New'
            },
            left: '100'
          },
          grid:{
            top:"50%"
          },
          radar: {
            // shape: 'circle',
            center: ['50%', '50%'],
            name: {
              textStyle: {
                color: '#fff',
                backgroundColor: '#999',
                borderRadius: 3,
                padding: [3, 5]
              }
            },
            indicator: [
              {name: '公共课程', max: 100},
              {name: '专业课程', max: 100},
              {name: '实体教学课程', max: 100},
              {name: '通识教育', max: 100},
              {name: '学科基础教育', max: 100},
            ]
          },
          series: [{
            name: '成绩',
            type: 'radar',
            areaStyle: {normal: {}},
            data: [
              {
                value: [this.gonggong, this.zhuanye, this.shijian, this.tongshi, this.xueke],
                name: '各类学科能力'
              }
            ]
          }]
        })
      }
    }
  }
</script>
