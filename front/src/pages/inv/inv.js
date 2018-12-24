// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Inv from './Inv.vue'
import router from './router'
import { Button } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui'
import VueResource from 'vue-resource'

Vue.config.productionTip = false
Vue.use(Button)
Vue.use(ElementUI)
Vue.use(VueResource)
/* eslint-disable no-new */
new Vue({
  el: '#inv',
  router,
  components: { Inv },
  template: '<Inv/>'
})
