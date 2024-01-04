<template>
  <el-dialog
    :append-to-body="true"
    :close-on-click-modal="false"
    :before-close="cancel"
    :visible.sync="dialog"
    :title="isAdd ? '新增' : '编辑'"
    width="80%"
  >
    <el-form ref="form" :model="form" size="small" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="form.title" style="width: 370px;"/>
      </el-form-item>
    </el-form>
    <mavon-editor :value="form.content" ref="md" :style="'height:' + height"/>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import common from '@/api/common/common'
  import MarkDown from './MarkDown'
  import notice from '@/api/common/notice'

  export default {
    components: {
      MarkDown
    },
    props: {
      isAdd: {
        type: Boolean,
        required: true
      }
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
      doSubmit() {
        this.loading = true
        if (this.isAdd) {
          this.doAdd()
        } else {
          this.doEdit()
        }
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
