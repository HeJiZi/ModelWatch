import Vue from 'vue'
import Sign from './Sign'
import { Button, Input } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import VueResource from 'vue-resource'
import router from './router'


Vue.config.productionTip = false

Vue.use(VueResource)
Vue.use(Button)
Vue.use(Input)
/* eslint-disable no-new */
new Vue({
  el: '#sign',
  router,
  components: { Sign },
  template: '<Sign/>'
})
