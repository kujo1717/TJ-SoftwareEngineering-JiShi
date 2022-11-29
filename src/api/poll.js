import request from '@/utils/request'

// 获取投票
export function getpoll(acitivity_id) {
  return request({
    url: '/api/poll/getpoll/' + acitivity_id.toString(),
    method: 'get',
    params: {}
  })
}
export function postPoll(param) {
  return request({
    url: '/api/poll/postPoll',
    method: 'post',
    data: param,
    headers: {
      TokenValue: ''
    }
  })
}
