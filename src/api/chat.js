import request from '@/utils/request'

export function getActivityAllPaticipant(activityId) {
    return request({
        url: '/api/activity/getActApplicantList',
        method: 'get',
        params: {
            activity_id: activityId
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

export function getActivityAllMessageBoard(activityId) {
    return request({
        url: '/api/chat/getActivityAllMessageBoard',
        method: 'get',
        params: {
            activityId: activityId,
        }
    })
}

export function sendMessageBoard(activityId, userId, content, createTime) {
    return request({
        url: '/api/chat/addMessageBoard',
        method: 'post',
        params: {
            activityId: activityId,
            userId: userId,
            content:content,
            createTime:createTime
        }
    })
}

