const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  age:state=>state.user.age,
  introduce:state=>state.user.introduce,
  id:state=>1145
}
export default getters