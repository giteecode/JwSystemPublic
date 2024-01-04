import request from '@/utils/request'

export function findStudentPanel() {
  return request({
    url: 'api/main/findStudentPanel',
    method: 'get',
  })
}

export function findUserPanel() {
  return request({
    url: 'api/main/findUserPanel',
    method: 'get',
  })
}

export function findTeacherPanel(params) {
  return request({
    url: 'api/main/findTeacherPanel',
    method: 'get',
    params: params
  })
}

export default {findStudentPanel, findTeacherPanel, findUserPanel}
