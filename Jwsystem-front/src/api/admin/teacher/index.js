import request from '@/utils/request'

export function pageQuery(params) {
  return request({
    url: 'api/teacher/pageQuery',
    method: 'get',
    params: params
  })
}

export function add(data) {
  return request({
    url: 'api/teacher',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/teacher',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/teacher',
    method: 'put',
    data
  })
}

export default {add, edit, del, pageQuery}
