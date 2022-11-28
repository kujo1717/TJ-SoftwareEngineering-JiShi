/** 
 * vuex管理
 * @author yy
 * @editor yy
 * @lastUpdate 10.15
*/
import Vue from "vue";
import Vuex from 'vuex';

import aside from './aside'
Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        aside
    }
})