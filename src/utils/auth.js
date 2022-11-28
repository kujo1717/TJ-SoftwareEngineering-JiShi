import Cookies from 'js-cookie'

<<<<<<< HEAD
const TokenKey = 'token'

export function getToken() {
  console.log("获取cookie token "+Cookies.get(TokenKey))
=======
const TokenKey = 'Admin-Token'

export function getToken() {
>>>>>>> lyf_front
  return Cookies.get(TokenKey)
}

export function setToken(token) {
<<<<<<< HEAD
  console.log("设置cookie token "+token)
=======
>>>>>>> lyf_front
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
