import request from '@/utils/request'

export function countCourseScore(params) {
  return request({
    url: 'api/count/countCourseScore',
    method: 'get',
    params:params
  })
}

export function countCommentByCourse(params) {
  return request({
    url: 'api/count/countCommentByCourse',
    method: 'get',
    params:params
  })
}

export default {countCourseScore,countCommentByCourse}
