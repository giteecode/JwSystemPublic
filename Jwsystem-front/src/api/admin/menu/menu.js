import request from '@/utils/request'

export function listajaxMenu() {
  return request({
    url: 'api/menu/listajax',
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'api/menu/add',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/menu/delete',
    method: 'delete',
    params: {
      menuId: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/menu/edit',
    method: 'put',
    data
  })
}

export default { add, edit, del, listajaxMenu }
