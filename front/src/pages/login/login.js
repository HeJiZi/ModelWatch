import Vue from 'vue'
import UserLogin from './UserLogin'
import { Button, Input } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import VueResource from 'vue-resource' 

Vue.config.productionTip = false

Vue.use(VueResource)
Vue.use(Button)
Vue.use(Input)
/* eslint-disable no-new */
new Vue({
  el: '#login',
  components: { UserLogin },
  template: '<UserLogin/>'
})
