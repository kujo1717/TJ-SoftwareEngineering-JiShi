import request from '@/utils/request'

export function getAllRubbish () {
  return request({
    url: '/api/dustbin/findAllRubbish',
    method: 'get',
  })
}

export function smashOneRubbish (id) {
  return request({
    url: '/api/dustbin/smashOneRubbish',
    method: 'delete',
    params: { id: id }
  })
}

export function smashAllRubbish () {
  return request({
    url: '/api/dustbin/clearDustbin',
    method: 'delete'
  })
}

export function restoreOneRubbish (id) {
  return request({
    url: '/api/dustbin/restoreOneRubbish',
    method: 'patch',
    params: { id: id }
  })
}