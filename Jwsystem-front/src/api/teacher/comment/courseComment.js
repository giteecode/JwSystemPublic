import request from '@/utils/request'

export function queryCourseComment(params) {
  return request({
    url: 'api/courseComment/queryCourseComment',
    method: 'get',
    params: params
  })
}

export function queryStudentComment(params) {
  return request({
    url: 'api/courseComment/queryCourseComment',
    method: 'get',
    params: params
  })
}

export function add(data) {
  return request({
    url: 'api/courseComment',
    method: 'post',
    data
  })
}

export function teacherReply(data) {
  return request({
    url: 'api/courseComment/teacherReply',
    method: 'post',
    data
  })
}

export default {add, queryCourseComment, queryStudentComment, teacherReply}
