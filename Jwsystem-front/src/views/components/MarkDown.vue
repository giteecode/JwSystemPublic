<template>
  <div class="app-container">
    <p class="warn-content">
      <!--      Markdown 基于-->
      <!--      <el-link type="primary" href="https://github.com/hinesboy/mavonEditor" target="_blank">MavonEditor</el-link>-->
      <!--      ，图片上传使用 <el-link type="primary" href="https://sm.ms/" target="_blank">SM.MS</el-link>-->
      <el-button type="primary" @click="save" v-if="specialtyId!=null">保存</el-button>
    </p>
    <mavon-editor :value="data" ref="md" :style="'height:' + height" @imgAdd="imgAdd"/>
  </div>
</template>

<script>
  import {upload} from '@/utils/upload'
  import {mapGetters} from 'vuex'
  import program from '@/api/admin/program/index'

  export default {
    props: ['data'],
    name: 'Markdown',
    data() {
      return {
        height: document.documentElement.clientHeight - 200 + 'px',
        specialtyId: null,
        program: null,
        form: {
          name: null,
          specialtyId: null
        }
      }
    },
    computed: {
      ...mapGetters([
        'imagesUploadApi'
      ])
    },
    mounted() {
      const that = this
      window.onresize = function temp() {
        that.height = document.documentElement.clientHeight - 200 + 'px'
      }
    },
    methods: {
      save() {
        this.form['specialtyId'] = this.specialtyId
        this.form['name'] = this.$refs.md._data['d_value']
        if (this.program != null && this.program != "") {
          this.program['name'] = this.$refs.md._data['d_value']
          this.program['htmlName'] = this.$refs.md._data['d_render']
          program.edit(this.program).then(res => {
            if (res) {
              this.$message({
                type: "success",
                message: "成功"
              })
            } else {
              this.$message({
                type: "danger",
                message: "失败"
              })
            }
          })
        } else {
          program.add(this.form).then(res => {
            if (res) {
              this.$message({
                type: "success",
                message: "成功"
              })
            } else {
              this.$message({
                type: "danger",
                message: "失败"
              })
            }
          })
        }
      },
      imgAdd(pos, $file) {
        upload(this.imagesUploadApi, $file).then(data => {
          this.$refs.md.$img2Url(pos, data.data.url)
        })
      }
    }
  }
</script>

<style scoped>
</style>
