import request from '@/utils/request'

export function pageQuery() {
  return request({
    url: 'api/comment/pageQuery',
    method: 'get',
  })
}

export function listTeamComment(data) {
  return request({
    url: 'api/teamComment/pageQuery',
    method: 'get',
    params:data
  })
}

export function add(data) {
  return request({
    url: 'api/classes/add',
    method: 'post',
    data
  })
}

export function addCourseComment(data) {
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

export default { add, edit, del,listTeamComment}
