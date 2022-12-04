import request from '@/utils/request'

export function postFile(formData) {
    return request({
        url: '/api/img/postImgList',
        method: 'post',
        headers: {
            "Content-Type": "multipart/form-data; boundary=----WebKitFormBoundaryn8D9asOnAnEU4Js0"
        },
        data: formData


    })
}


//获取图片
export function getImg(path) {
    return request({
        url: '/api/img/getImg',
        method: 'get',
        params: {
            path: path,
        }
    })
}