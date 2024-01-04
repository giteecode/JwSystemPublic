<template>
  <el-row>
    <el-col :span="14">
      <!--row1-->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card align="center" style="height: 185px">
            <img src="../../../assets/images/avatar.png"/>
            <div style="padding: 14px;">
              <span>{{user.student.username}}</span>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-card class="top-card-middle">
                <div style="position: absolute;right: 2px;top: 2px;font-size: 14px;font-weight: bold">
                  <i style="font-size: 1.5em;" class="el-icon-user"></i>
                </div>
                <div align="center" class="top-card-content">
                  <span>{{user.student.sex}}</span>
                </div>
                <div style="position: absolute;left: 2px;bottom: 2px;font-size: 14px;">
                  性别
                </div>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card class="top-card-middle">
                <div style="position: absolute;right: 2px;top: 2px;font-size: 14px;font-weight: bold">
                  <i style="font-size: 1.5em;" class="el-icon-notebook-2"></i>
                </div>
                <div align="center" class="top-card-content">
                  <span>{{user.specialty.name}}</span>
                </div>
                <div style="position: absolute;left: 2px;bottom: 2px;font-size: 14px;">
                  专业名称
                </div>
              </el-card>
            </el-col>
          </el-row>
          <el-row :gutter="20" style="margin-top: 10px">
            <el-col :span="12">
              <el-card class="top-card-middle-1">
                <div style="position: absolute;right: 2px;top: 2px;font-size: 14px;font-weight: bold">
                  <i style="font-size: 1.5em;" class="el-icon-user"></i>
                </div>
                <div align="center" class="top-card-content">
                  <span>汉</span>
                </div>
                <div style="position: absolute;left: 2px;bottom: 2px;font-size: 14px;">
                  民族
                </div>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card class="top-card-middle-1">
                <div style="position: absolute;right: 2px;top: 2px;font-size: 14px;font-weight: bold">
                  <i style="font-size: 1.5em;" class="el-icon-school"></i>
                </div>
                <div align="center" class="top-card-content">
                  <span>本科</span>
                </div>
                <div style="position: absolute;left: 2px;bottom: 2px;font-size: 14px;">
                  学历层次
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="8">
<!--          <van-circle  :rate="30" :speed="100" />-->
          <zhnl></zhnl>
        </el-col>
      </el-row>
      <!--row2-->
      <el-row :gutter="20" style="margin-top: 30px">
        <el-row :gutter="10">
          <el-col :span="5">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>{{user.courseNum}}</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                课程数
              </div>
            </el-card>
          </el-col>
          <el-col :span="5">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>{{user.totalTime}}</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                总学时
              </div>
            </el-card>
          </el-col>
          <el-col :span="5">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>{{user.upCourseRate}}%</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                到课率
              </div>
            </el-card>
          </el-col>
          <el-col :span="5">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>{{user.eligiableRate}}%</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                合格率
              </div>
            </el-card>
          </el-col>
          <el-col :span="4">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>600</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                奖学金
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="10" style="margin-top: 10px">
          <el-col :span="5">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>14215</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                上网时间
              </div>
            </el-card>
          </el-col>
          <el-col :span="5">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>0</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                缴费及欠费
              </div>
            </el-card>
          </el-col>
          <el-col :span="5">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>{{user.disciplinary}}</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                违纪
              </div>
            </el-card>
          </el-col>
          <el-col :span="5">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>99</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                心理卫生
              </div>
            </el-card>
          </el-col>
          <el-col :span="4">
            <el-card class="middle-card-middle">
              <div align="center" class="middle-card-content">
                <span>10</span>
              </div>
              <div align="center" style="color: #1f2d3d;font-size: 12px">
                校园卡余额
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row :gutter="10" style="margin-top: 10px">
          <kao-qin :data="user.absentCount"></kao-qin>
        </el-row>
      </el-row>
    </el-col>
    <el-col :span="10" align="center">
      <zong-he></zong-he>
      <div>
        <cheng-ji :tongshi="user.tongshiRate" :shijian="user.shijanRate" :xueke="user.xuekeRate"
                  :zhuanye="user.zhuanyeRate" :gonggong="user.gonggongRate"></cheng-ji>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  import zhnl from './zhnl'
  import KaoQin from './KaoQin'
  import ChengJi from './ChengJi'
  import ZongHe from './ZongHe'
  import count from '@/api/count/count'

  export default {
    components: {
      zhnl,
      KaoQin,
      ChengJi,
      ZongHe
    },
    data() {
      return {
        user: null
      }
    },
    created() {
      count.findStudentPanel().then(res => {
        this.user = res;
      })
    },
    mounted() {
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
  section{width:2rem;height:2rem;position: relative;margin:2rem;}
  .wrap,.circle,.percent{position: absolute; width: 2rem; height: 2rem; border-radius: 50%;}
  .wrap{top:0; left:0; background-color:#ccc;}
  .circle{box-sizing: border-box; -webkit-box-sizing:border-box; -ms-box-sizing:border-box; -moz-box-sizing:border-box; -o-box-sizing:border-box; border:1px solid #ccc; clip:rect(0,2rem,2rem,1rem);}
  .clip-auto{clip:rect(auto, auto, auto, auto);} .percent{box-sizing: border-box; -webkit-box-sizing:border-box; -ms-box-sizing:border-box; -moz-box-sizing:border-box; -o-box-sizing:border-box; top:-1px; left:-1px;}
  .left{border:0.1rem solid #ff6300; clip: rect(0,1rem,2rem,0);} .right{border:0.1rem solid #FF6300; clip: rect(0,2rem,2rem,1rem);}
  .wth0{width:0;}
  .num{position: absolute; box-sizing: border-box; -webkit-box-sizing:border-box; -ms-box-sizing:border-box; -moz-box-sizing:border-box; -o-box-sizing:border-box; width:1.8rem; height:1.8rem; line-height:1.8rem; text-align: center; font-size: 0.5rem; left:0.1rem; top:0.1rem; border-radius: 50%; color:#FF6300; background: white; z-index: 1;}
</style>
