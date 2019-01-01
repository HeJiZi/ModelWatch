import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { Menu,MenuItem,Loading } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Loading)
/* eslint-disable no-new */
new Vue({
  el: '#index',
  router,
  components: { App },
  template: '<App/>'
})
