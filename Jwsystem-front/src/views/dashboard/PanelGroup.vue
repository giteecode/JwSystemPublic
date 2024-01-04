<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="12" :sm="12" :lg="12" class="card-panel-col">
      <heap></heap>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="12" class="card-panel-col">
      <non-heap></non-heap>
    </el-col>
  </el-row>
</template>

<script>
  import CountTo from 'vue-count-to'
  import { get } from '@/api/monitor/visits'
  import Heap from '../../components/Echarts/heap'
  import NonHeap from '../../components/Echarts/nonheap'

  export default {
    components: {
      NonHeap,
      Heap,
      CountTo
    },
    data() {
      return {
        count: { newIp: 0, newVisits: 0, recentIp: 0, recentVisits: 0 }
      }
    },
    mounted() {
      get().then(res => {
        this.count.newIp = res.newIp
        this.count.newVisits = res.newVisits
        this.count.recentIp = res.recentIp
        this.count.recentVisits = res.recentVisits
      })
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .panel-group {
    margin-top: 18px;

    .card-panel-col {
      margin-bottom: 32px;
    }

    .card-panel {
      height: 108px;
      font-size: 12px;
      position: relative;
      overflow: hidden;
      color: #666;
      background: #fff;
      box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
      border-color: rgba(0, 0, 0, .05);

      .icon-people {
        color: #40c9c6;
      }

      .icon-message {
        color: #36a3f7;
      }

      .icon-money {
        color: #f4516c;
      }

      .icon-shopping {
        color: #34bfa3
      }

      .card-panel-icon-wrapper {
        float: left;
        margin: 14px 0 0 14px;
        padding: 16px;
        transition: all 0.38s ease-out;
        border-radius: 6px;
      }

      .card-panel-icon {
        float: left;
        font-size: 48px;
      }

      .card-panel-description {
        float: right;
        font-weight: bold;
        margin: 26px;
        margin-left: 0px;

        .card-panel-text {
          line-height: 18px;
          color: rgba(0, 0, 0, 0.45);
          font-size: 16px;
          margin-bottom: 12px;
        }

        .card-panel-num {
          font-size: 20px;
        }
      }
    }
  }
</style>
