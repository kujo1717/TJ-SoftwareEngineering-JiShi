import request from '@/utils/request'

export function getOneGroupAllUser(groupId) {
    return request({
        url: '/api/Contact/getOneGroupAllUser',
        method: 'get',
        params: {
            groupId: groupId
        }
    })
}

export function getUserInfoById(userId) {
    return request({
        url: '/api/user/finduser',
        method: 'get',
        params:{
            userId: userId
        }
    })
}

