import request from '@/utils/request'

export function findStudentPlan(params) {
  return request({
    url: 'api/planCourse/findStudentPlan',
    method: 'get',
    params:params
  })
}

export default { findStudentPlan}
