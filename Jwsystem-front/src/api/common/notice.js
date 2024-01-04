import request from '@/utils/request'

export function findNoticeByJW(collegeId) {
  return request({
    url: 'api/userNotice/findNoticeByJW',
    method: 'get',
  })
}

export function findNoticeByStudent(collegeId) {
  return request({
    url: 'api/userNotice/findNoticeByStudent',
    method: 'get',
  })
}

export function findNoticeByTeacher(collegeId) {
  return request({
    url: 'api/userNotice/findNoticeByTeacher',
    method: 'get',
  })
}

export function add(data) {
  return request({
    url: 'api/userNotice',
    method: 'post',
    data
  })
}

export function addNotice(data) {
  return request({
    url: 'api/notice',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/userNotice',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/userNotice',
    method: 'put',
    data
  })
}

export function delNotice(id) {
  return request({
    url: 'api/notice',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function editNotice(data) {
  return request({
    url: 'api/notice',
    method: 'put',
    data
  })
}

export default {
  findNoticeByJW,
  findNoticeByStudent,
  findNoticeByTeacher,
  add,
  del,
  edit,
  addNotice,
  delNotice,
  editNotice
}
