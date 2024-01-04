import request from '@/utils/request'

export function listajax() {
  return request({
    url: 'api/college/listajax',
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'api/user/add',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/user/delete',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/user/edit',
    method: 'put',
    data
  })
}

export default { add, edit, del, listajax }
