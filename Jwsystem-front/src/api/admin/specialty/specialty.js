import request from '@/utils/request'

export function listajax() {
  return request({
    url: 'api/college/listajax',
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'api/specialty/addSpecialty',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/specialty/delete',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function redo(id) {
  return request({
    url: 'api/specialty/redoSpecialty',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/specialty/updateSpecialty',
    method: 'post',
    data
  })
}

export default { add, edit, del }
