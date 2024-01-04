import request from '@/utils/request'
import da from 'element-ui/src/locale/lang/da'

export function login(username, password, code, RadioButtonList1) {
  let data = new FormData()
  data.append('username', username)
  data.append('password', password)
  data.append('checkcode', code)
  data.append('RadioButtonList1', RadioButtonList1)
  return request({
    url: '/api/login',
    method: 'post',
    data: data
  })
}

export function getInfo() {
  return request({
    url: 'api/info',
    method: 'get'
  })
}

export function getCodeImg() {
  return request({
    url: 'kapatch/code',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: 'api/logout',
    method: 'delete'
  })
}
