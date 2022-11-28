import request from '@/utils/request'

export function getSystemInfo(userId) {
    return request({
        url: '/api/notice/getSystemNoticeByUserID',
        method: 'get',
        params: {
            userId: userId
        }
    })
}

export function getActivityInfo(userId) {
    return request({
        url: '/api/notice/getActivityNoticeByUserID',
        method: 'get',
        params: {
            userId: userId
        }
    })
}

export function getItemInfo(userId) {
    return request({
        url: '/api/notice/getItemNoticeByUserID',
        method: 'get',
        params: {
            userId: userId
        }
    })
}

export function modifyNoticeStatus(userId, noticeId) {
    return request({
        url: '/api/notice/modifyNoticeStatus',
        method: 'patch',
        params: {
            userId: userId,
            noticeId: noticeId
        }
    })
}

export function modifyItemNoticeStatus(itemNoticeId) {
    return request({
        url: '/api/notice/modifyItemNoticeStatus',
        method: 'patch',
        params: {
            itemNoticeId: itemNoticeId
        }
    })
}