import request from '@/utils/request'

export function findInfo() {
  return request({
    url: 'api/student/findInfo',
    method: 'get'
  })
}

export default { findInfo}
