import request from '@/utils/request'

export function listajax() {
  return request({
    url: 'api/college/listajax',
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'api/college/saveOrUpdateCollege',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/college/deleteCollege',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/college/editCollege',
    method: 'post',
    data
  })
}

export default { add, edit, del }
