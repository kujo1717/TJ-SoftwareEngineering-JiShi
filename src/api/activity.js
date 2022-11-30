import request from '@/utils/request'



//获取活动列表,用户所有的，即参与的+报名的
export function getActList_All(user_id, state) {
    return request({
        url: '/api/activity/getAllActList',
        method: 'get',
        params: {
            user_id: user_id,
            state: state
        }
    })
}


//获取活动列表,用户创建的
export function getActList_Create(user_id, state) {
    return request({
        url: '/api/activity/getAllCreate',
        method: 'get',
        params: {
            user_id: user_id,
            state: state
        }
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

//获取用户 报名的所有活动
export function getActList_Apply(user_id, state) {
    return request({
        url: '/api/activity/getAllApply',
        method: 'get',
        params: {
            user_id: user_id,
            state: state
        }
    })
}

//获取用户 参与的所有活动
export function getActList_Participate(user_id, state) {
    return request({
        url: '/api/activity/getAllParticipate',
        method: 'get',
        params: {
            user_id: user_id,
            state: state
        }
    })
}


//根据活动id，获取活动详细内容
export function getActDetail(activity_id, user_id) {
    return request({
        url: '/api/activity/getActDetail/' + activity_id.toString() + '/' + user_id.toString(),
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




//获取参与某活动的所有用户列表
export function getActApplicantList(activity_id) {
    return request({
        url: '/api/activity/getActApplicantList',
        method: 'get',
        params: {
            activity_id: activity_id
        },
    })
}