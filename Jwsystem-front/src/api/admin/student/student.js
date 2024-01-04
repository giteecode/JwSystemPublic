import request from '@/utils/request'

export function listajaxCollege() {
  return request({
    url: 'api/college/listajax',
    method: 'get'
  })
}

export function listajaxSpecialty(collegeId) {
  return request({
    url: 'api/specialty/listajax',
    method: 'get',
    params: {
      'status': '1',
      'collegeId': collegeId
    }
  })
}

export function listajaxGrade() {
  return request({
    url: 'api/grade/listajax',
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'api/classes/add',
    method: 'post',
    data
  })
}

export function del(id) {
  return request({
    url: 'api/classes/deleteCollege',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function edit(data) {
  return request({
    url: 'api/classes/editCollege',
    method: 'post',
    data
  })
}

export default { add, edit, del, listajaxCollege, listajaxSpecialty, listajaxGrade }
