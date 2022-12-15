import request from '@/utils/request'

export function friendList(id) {
    return request({
      url: '/friend',
      method: 'get',
      params: { id }
    })
  }
export function friendManage(data){
  return request({
    url:'/friend/manage',
    method:'post',
    data
  })
}