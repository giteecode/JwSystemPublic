<template>
  <div class="dashboard-container">
    <div class="dashboard-editor-container" v-if="qx == '管理员'">
      <panel-group />
      <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
        <line-chart />
      </el-row>
    </div>
    <div class="dashboard-editor-container" v-if="qx == '教务人员'">
      <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
        <jw-panel />
      </el-row>
    </div>
    <div class="dashboard-editor-container" v-if="qx == '讲师'">
      <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
        <teacher-panel />
      </el-row>
    </div>
    <div class="dashboard-editor-container" v-if="qx == '学生'">
      <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
       <student-panel/>
      </el-row>
    </div>
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import jwPanel from './dashboard/jw/jwPanel'
import studentPanel from './dashboard/student/studentPanel'
import teacherPanel from './dashboard/teacher/teacherPanel'

// import RadarChart from '@/components/Echarts/RadarChart'
// import PieChart from '@/components/Echarts/PieChart'
// import BarChart from '@/components/Echarts/BarChart'
import { count } from '@/api/monitor/visits'

/**
 * 记录访问，只有页面刷新或者第一次加载才会记录
 */
count().then(res => {
})

export default {
  name: 'Dashboard',
  data(){
    return{
      qx:null
    }
  },
  components: {
    PanelGroup,
    LineChart,
    jwPanel,
    studentPanel,
    teacherPanel
  },
  created() {
      this.qx = this.$store.state.user.user.qx
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .dashboard-editor-container {
    padding: 18px 22px 22px 22px;
    background-color: rgb(240, 242, 245);
    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }
</style>
