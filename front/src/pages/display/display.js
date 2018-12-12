// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Display from './Display.vue'
import router from './router'
import { Button,MenuItem,Menu,Tree,Input,Pagination } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';


Vue.config.productionTip = false
Vue.use(Button)
Vue.use(MenuItem)
Vue.use(Menu)
Vue.use(Tree)
Vue.use(Input)
Vue.use(Pagination)
/* eslint-disable no-new */
new Vue({
  el: '#display',
  router,
  components: { Display },
  template: '<Display/>'
})
