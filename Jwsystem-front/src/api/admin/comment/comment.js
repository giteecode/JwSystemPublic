import request from '@/utils/request'

export function pageQuery(params) {
  return request({
    url: 'api/comment/pageQuery',
    method: 'get',
    params:params
  })
}

export function findTeacher(data) {
  return request({
    url: 'api/teamComment/findTeacher',
    method: 'get',
    params: data
  })
}

export function findStudentComment(data) {
  return request({
    url: 'api/teamComment/findStudentComment',
    method: 'get',
    params: data
  })
}

export function add(data) {
  return request({
    url: 'api/comment',
    method: 'post',
    data
  })
}


export function del(id) {
  return request({
    url: 'api/comment',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/comment',
    method: 'put',
    data
  })
}

export default {add, edit, del, findTeacher, findStudentComment}
