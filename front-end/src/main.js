// // 引入element样式文件
import 'element-ui/lib/theme-chalk/index.css'
import './assets/styles/icon/iconfont.css'
import './assets/styles/css/element-variables.scss'
import Vue from 'vue'
import App from './App.vue'
import http from 'axios'
import VueCookies from 'vue-cookies'
// 按需引入Element-ui
import {
  Button, Container, Header, Main, Footer, Aside, Menu, Submenu, MenuItem, MenuItemGroup,
  Dropdown, DropdownMenu, DropdownItem, Row, Col, Card, Message, MessageBox, Dialog, Steps, Step,
  Form, FormItem, Input, Select, Checkbox, Radio, Switch, DatePicker, TimePicker,
  Tabs, TabPane, Table, TableColumn, Descriptions, DescriptionsItem, Icon, Tooltip, Alert, Tag,
  Option, Avatar, Image, Skeleton, Divider, Calendar, Carousel, CarouselItem, Rate, Autocomplete, Popover, Pagination,
  Badge, Upload, CollapseItem, Collapse, Loading, RadioGroup, RadioButton, Cascader, CascaderPanel
} from 'element-ui'

import router from './router/router'
import store from './store/index'
//注册使用ElementUI
Vue.use(CascaderPanel)
Vue.use(Cascader)
Vue.use(RadioGroup)
Vue.use(RadioButton)
Vue.use(Button);
Vue.use(Container);
Vue.use(Header);
Vue.use(Main);
Vue.use(Footer);
Vue.use(Aside);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.use(Dropdown);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(Row);
Vue.use(Col);
Vue.use(Card);
Vue.use(Dialog);
Vue.use(Step);
Vue.use(Steps);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Select);
Vue.use(Checkbox);
Vue.use(Radio);
Vue.use(Switch);
Vue.use(DatePicker);
Vue.use(TimePicker);
Vue.use(Tabs);
Vue.use(TabPane);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Descriptions);
Vue.use(DescriptionsItem);
Vue.use(Icon);
Vue.use(Tooltip);
Vue.use(Alert);
Vue.use(Tag);
Vue.use(Option);
Vue.use(Avatar);
Vue.use(Image);
Vue.use(Skeleton);
Vue.use(Divider);
Vue.use(Carousel);
Vue.use(CarouselItem);
Vue.use(Calendar);
Vue.use(Rate);
Vue.use(Autocomplete);
Vue.use(Popover);
Vue.use(Pagination);
Vue.use(Badge);
Vue.use(Upload);
Vue.use(CollapseItem);
Vue.use(Collapse);
Vue.use(Loading.directive);


//引入moment
import moment from "moment"
Vue.prototype.$moment = moment;

Vue.prototype.$message = Message;
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$alert = MessageBox.alert
Vue.prototype.$loading = Loading.service
//注册axios
Vue.prototype.$http = http

Vue.use(VueCookies)
Vue.config.productionTip = false

new Vue({
  store,//Vuex注册
  router,//全局注册router
  render: h => h(App)
}).$mount('#app')
// Vue.use(Message);
Vue.prototype.$msgbox = MessageBox;
// 否则每次进入页面时会自动出现 message,messageBox 提示

