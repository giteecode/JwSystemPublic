import request from '@/utils/request'

export function findMenuByRole() {
  return request({
    url: 'api/role/findMenuByRole',
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'api/role/add',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/role/delete',
    method: 'delete',
    params: {
      roleId: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/role/edit',
    method: 'put',
    data
  })
}

export function saveMenu(data) {
  return request({
    url: 'api/role/saveMenu',
    method: 'post',
    data
  })
}

export default { add, edit, del, saveMenu }
