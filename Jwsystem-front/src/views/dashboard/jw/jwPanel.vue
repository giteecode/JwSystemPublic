<template>
  <div>
    <el-row>
      <!--逃课排行榜-->
      <el-col :span="24">
        <exit-course :data="data.absentCount"></exit-course>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-row>
          <!--旷课最多的时段-->
          <el-col :span="12">
            <de-course-time :data="data.downCourseSectionRate"></de-course-time>
          </el-col>
          <!--旷课最多的课程-->
          <el-col :span="10" :offset="2">
            <de-course :data="data.downCourseRate"></de-course>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="16">
        <el-card style="margin: 10px 0px">
          <div slot="header" class="clearfix">
            <span class="role-span">通知公告</span>
            <el-button size="mini" type="primary" icon="el-icon-plus" style="margin-left: 10px" @click="add">
              发布公告
            </el-button>
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
      <!--在校人数-->
      <el-col :span="8">
        <people :data="data.countGrade"></people>
      </el-col>
    </el-row>
    <e-from :is-add="isAdd" @close="load" ref="form"></e-from>
  </div>
</template>

<script>
  import ExitCourse from './ExitCourse'
  import deCourse from './deCourse'
  import deCourseTime from './deCourseTime'
  import People from './People'
  import count from '@/api/count/count'
  import eFrom from './form'
  import service from '../../../utils/request'

  export default {
    components: {
      ExitCourse,
      deCourseTime,
      deCourse,
      People,
      eFrom
    },
    data() {
      return {
        data: null,
        isAdd: true,
      }
    },
    created() {
      count.findUserPanel().then(res => {
        this.data = res
      })
    },
    mounted() {
    },
    methods: {
      load() {
        service.get("api/userNotice/findNoticeByJW").then(res => {
          this.data['noticeList'] = res;
        })
      },
      add() {
        this.isAdd = true
        this.$refs.form.dialog = true
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
