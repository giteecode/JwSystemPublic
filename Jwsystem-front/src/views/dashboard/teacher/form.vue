<template>
  <el-dialog
    :append-to-body="true"
    :close-on-click-modal="false"
    :before-close="cancel"
    :visible.sync="dialog"
    width="80%"
  >
    <div v-html="form.content" style="border: solid 1px #c7e1f5;padding: 20px;width: 80%;margin: 0 auto;"></div>
  </el-dialog>
</template>

<script>
  import common from '@/api/common/common'
  import notice from '@/api/common/notice'

  export default {
    components: {
    },
    created() {
      common.listajaxYear().then(res => {
        this.year = res
      })
    },
    data() {
      return {
        loading: false, dialog: false, year: [],
        height: document.documentElement.clientHeight - 200 + 'px',
        form: {
          id: null,
          title: null,
          content: null,
          htmlContent: null,
          user: null
        },
        options: [{
          id: 0,
          value: "学生",
        }, {
          id: 1,
          value: "教师",
        },],
        data: null,
        rules: {}
      }
    },
    methods: {
      cancel() {
        this.resetForm()
      },
      doAdd() {
        this.form.content = this.$refs.md._data['d_value']
        this.form.htmlContent = this.$refs.md._data['d_render']
        notice.addNotice(this.form).then(res => {
          this.resetForm()
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
        this.$emit('close')
      },
      doEdit() {
        this.form.content = this.$refs.md._data['d_value']
        this.form.htmlContent = this.$refs.md._data['d_render']
        notice.editNotice(this.form).then(res => {
          this.resetForm()
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
        this.$emit('close')
      },
      resetForm() {
        this.loading = false
        this.dialog = false
        this.$refs['form'].resetFields()
        this.form = {
          id: null,
          title: null,
          content: null,
          htmlContent: null,
          user: null
        }
      }
    }
  }
</script>

<style scoped>

</style>
