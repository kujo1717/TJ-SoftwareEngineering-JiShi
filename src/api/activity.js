import request from '@/utils/request'


//获取全部活动
export function getAll() {
    return request({
        url: '/api/activity/getAll',
        method: 'get',
        params: {}
    })
}

//活动brief的列表，用户创建的
export function getActBrief_UserCreate(id) {
    return request({
        url: '/api/activity/getUserCreateActBrief/' + id.toString(),
        method: 'get',
        params: {}
    })
}

//活动brief的列表，测试用的
export function getActBrief_Test(id) {
    return request({
        url: '/api/activity/getActBriefTest/' + id.toString(),
        method: 'get',
        params: {}
    })
}

//根据活动id，获取活动详细内容
export function getActDetail(activity_id,user_id) {
    return request({
        url: '/api/activity/getActDetail/' + activity_id.toString()+'/'+user_id.toString(),
        method: 'get',

    })
}


//新建一个活动，输入Activity实体
export function postAct(param) {
    return request({
        url: '/api/activity/postAct',
        method: 'post',
        data: param,
        headers: {
            TokenValue: '',
        }
    })
}

//更新一个活动信息，输入Activity实体
export function patchAct(param) {
    return request({
        url: '/api/activity/patchOneAct',
        method: 'patch',
        data: param,
        headers: {
            TokenValue: '',
        }
    })
}

//判断用户是不是活动的创建者
export function getIsCreator(user_id, activity_id) {
    return request({
        url: '/api/activity/iscreator',
        method: 'get',
        params: {
            user_id: user_id,
            activity_id: activity_id
        }
    })
}

//判断用户是不是活动的报名者
export function getIsApplicant(user_id, activity_id) {
    return request({
        url: '/api/activity/isapplicant',
        method: 'get',
        params: {
            user_id: user_id,
            activity_id: activity_id
        }
    })
}




//用户报名一个活动
export function postActivityApply(param) {
    return request({
        url: '/api/activity/postActApply',
        method: 'post',
        data: param,
        headers: {
            TokenValue: '',
        }
    })
}

//用户取消报名活动
export function deleteActivityApply(user_id, activity_id) {
    return request({
        url: '/api/activity/cancelApply',
        method: 'delete',
        params: {
            user_id: user_id,
            activity_id: activity_id
        },
        headers: {
            TokenValue: '',
        }
    })
}

//活动创建人，终止报名
export function patchActStopApply(param) {
    return request({
        url: '/api/activity/creatorStopApply',
        method: 'patch',
        params: param,
        headers: {
            TokenValue: '',
        }
    })
}


//活动创建人，活动结束
export function patchActFinish(param) {
    return request({
        url: '/api/activity/creatorFinishAct',
        method: 'patch',
        params: param,
        headers: {
            TokenValue: '',
        }
    })
}





//删除活动
export function deleteActivity(user_id, activity_id) {
    return request({
        url: '/api/activity/deleteAct',
        method: 'delete',
        params: {
            user_id: user_id,   
            activity_id: activity_id
        },
        headers: {
            TokenValue: '',
        }
    })
}

