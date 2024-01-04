import request from '@/utils/request'

export function listajaxSection(collegeId) {
  return request({
    url: 'api/section/pageQuery',
    method: 'get',
  })
}

export function listajaxTeam() {
  return request({
    url: 'api/team/pageQuery',
    method: 'get'
  })
}

export function listajaxWeek() {
  return request({
    url: 'api/week/pageQuery',
    method: 'get'
  })
}

export function listajaxCourseSystem() {
  return request({
    url: 'api/courseSystem/listajax',
    method: 'get'
  })
}

export function listajaxYear() {
  return request({
    url: 'api/year/listajax',
    method: 'get'
  })
}

export function listajaxCourse(params) {
  return request({
    url: 'api/course/listajax',
    method: 'get',
    params: params
  })
}

export function listajaxGrade() {
  return request({
    url: 'api/grade/listajax',
    method: 'get'
  })
}


export default {listajaxTeam, listajaxWeek, listajaxSection, listajaxCourseSystem, listajaxYear, listajaxCourse,listajaxGrade}
