import request from '@/utils/request'
import {findApply} from "../../admin/course/course";

export function findCourseByteacherId() {
  return request({
    url: 'api/teacher/findCourseByteacherId',
    method: 'get'
  })
}

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

export function findApplyByTeacher(params) {
  return request({
    url: 'api/teacherCourse/findApplyByTeacher',
    method: 'get',
    params: params
  })
}

export function teacherCourseAdd(data) {
  return request({
    url: 'api/teacherCourse',
    method: 'post',
    data
  })
}

export function teacherCourseEdit(data) {
  return request({
    url: 'api/teacherCourse',
    method: 'put',
    data
  })
}

export function teacherCourseAgree(id) {
  return request({
    url: 'api/teacherCourse/agree',
    method: 'put',
    params: {
      "id": id
    }
  })
}

export function teacherCourseBack(id) {
  return request({
    url: 'api/teacherCourse/back',
    method: 'put',
    params: {
      "id": id
    }
  })
}

export function teacherCourseDelete(params) {
  return request({
    url: 'api/teacherCourse',
    method: 'delete',
    params: params
  })
}

export default {
  add,
  edit,
  del,
  teacherCourseAdd,
  teacherCourseEdit,
  teacherCourseDelete,
  findApplyByTeacher,
  teacherCourseAgree,
  teacherCourseBack
}
