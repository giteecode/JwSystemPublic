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
        default: '350px'
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
          title:{
            text:'综合能力发展模型',
            textStyle:{
              color:'black',
              fontFamily:'Courier New'
            },
            left:'30%',
          },
          tooltip: {
            trigger: 'axis'
          },
          grid:{
            containLabel: true
          },
          radar: [
            {
              indicator: [
                {text: '个人发展', max: 100},
                {text: '职业发展', max: 100},
                {text: '社会发展', max: 100},
                {text: '学会发展', max: 100}
              ],
              center: ['50%', '50%'],
              radius: 80
            },
          ],
          series: [ {
            type: 'radar',
            tooltip: {
              trigger: 'item'
            },
            data: [
              {
                value: [60, 73, 85, 40],
                name: '综合能力发展模型'
              }
            ]
          },]
        })
      }
    }
  }
</script>
