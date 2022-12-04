import request from '@/utils/request'

export function getOneGroupAllUser(groupId, userId) {
    return request({
        url: '/api/Group/getOneGroupAllUser',
        method: 'get',
        params: {
            groupId: groupId,
            userId: userId
        }
    })
}

export function getUserInfoById(userId) {
    return request({
        url: '/api/user',
        method: 'get',
        params: {
            userId: userId
        }
    })
}

export function getActivityAllMessage(activityId) {
    return request({
        url: '/api/chat/getActivityAllMessage',
        method: 'get',
        params: {
            activityId: activityId
        }
    })
}

export function sendMessage(activityId, senderId, status, type, sendTime, content) {
    return request({
        url: '/api/chat/addMessage',
        method: 'post',
        params: {
            activityId: activityId,
            senderId: senderId,
            status: status,
            type: type,
            sendTime: sendTime,
            content: content
        }
    })
}

