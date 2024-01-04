import request from '@/utils/request'

export function findInfo() {
  return request({
    url: 'api/teacher/findById',
    method: 'get'
  })
}

export default { findInfo}
