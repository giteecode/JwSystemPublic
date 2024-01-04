<template>
  <div>
    <el-select v-model="params.teamId" placeholder="请选择" @change="change">
      <el-option
        v-for="item in team"
        :key="item.id"
        :label="item.name"
        :value="item.id"
      >
      </el-option>
    </el-select>
    <el-row>
      <!--教学进度完成-->
      <el-col :span="12">
        <jiao-xue :data="data.teacherSchedule"></jiao-xue>
      </el-col>
      <!--课程缺课情况-->
      <el-col :span="12">
        <kao-qin :courseData="data.courseList"></kao-qin>
      </el-col>
      <!--        <el-row>-->

      <!--          &lt;!&ndash;缺勤课程最多前5&ndash;&gt;-->
      <!--          <el-col :span="12">-->
      <!--            <course></course>-->
      <!--          </el-col>-->
      <!--          &lt;!&ndash;课程考勤&ndash;&gt;-->
      <!--          <el-col :span="12">-->
      <!--            <kao-qin/>-->
      <!--          </el-col>-->
      <!--        </el-row>-->
    </el-row>
    <el-row>
      <el-col :span="12"></el-col>
      <el-col :span="12">
        <el-card style="margin: 10px 0px">
          <div slot="header" class="clearfix">
            <span class="role-span">通知公告</span>
          </div>
          <el-table
            :data="data.noticeList"
            style="width: 100%">
            <el-table-column
              prop="createTime"
              label="发布日期"
              width="180">
            </el-table-column>
            <el-table-column
              prop="title"
              label="标题"
              width="180">
            </el-table-column>
            <el-table-column
              prop="username"
              label="发布人">
            </el-table-column>
            <el-table-column
              prop="username"
              label="发布人">
              <template slot-scope="scope">
                <el-button size="mini" type="primary" @click="edit(scope.row)">查看详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <e-from ref="form"></e-from>
  </div>
</template>

<script>
  import KaoQin from './KaoQin'
  import JiaoXue from './JiaoXue'
  import PingJia from './PingJia'
  import Course from './Course'
  import count from '@/api/count/count'
  import eFrom from './form'
  import common from '@/api/common/common'

  export default {
    components: {
      Course,
      JiaoXue,
      KaoQin,
      PingJia,
      eFrom
    },
    data() {
      return {
        data: null,
        params: {
          teamId: null
        },
        team: []
      }
    },
    mounted() {
      common.listajaxTeam().then(res => {
        this.team = res.records;
        this.params.teamId = this.team[this.team.length - 1].id
        this.load()
      })
    },

    methods: {
      load() {
        count.findTeacherPanel(this.params).then(res => {
          this.data = res
          console.log(this.$refs.kaoqin)
          this.$refs.kaoqin.data = res.courseList
          this.$refs.kaoqin.initChart()
        })
      },
      change(val) {

      },
      edit(data) {
        this.isAdd = false;
        const _this = this.$refs.form;
        _this.form = data;
        _this.dialog = true
      },
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .top-card-middle {
    position: relative;
    color: #77CDD6;
  }

  .top-card-middle-1 {
    position: relative;
    color: #E3AEA1
  }

  .middle-card-middle {
    position: relative;
    color: #60ABD9;
  }

  .top-card-content {
    padding: 15px 5px;
    font-size: 12px;
    font-weight: bold;
  }

  .middle-card-content {
    padding: 15px 5px;
    font-size: 24px;
    font-weight: bold;
  }
</style>
