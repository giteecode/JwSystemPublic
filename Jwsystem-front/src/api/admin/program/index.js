import request from '@/utils/request'

export function findProgram(params) {
  return request({
    url: 'api/program/findProgram',
    method: 'get',
    params: params
  })
}

export function add(data) {
  return request({
    url: 'api/program',
    method: 'post',
    data
  })
}

export function edit(data) {
  return request({
    url: 'api/program',
    method: 'put',
    data
  })
}

export function subDelete(id) {
  return request({
    url: 'api/program',
    method: 'delete',
    params: {
      "id": id
    }
  })
}

export default {add, edit, subDelete, findProgram}
