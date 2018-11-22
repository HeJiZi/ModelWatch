// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Project from './ProjectOv'
import router from './router'
import { Button,MenuItem,Menu } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';


Vue.config.productionTip = false
Vue.use(Button)
Vue.use(MenuItem)
Vue.use(Menu)
/* eslint-disable no-new */
new Vue({
  el: '#project',
  router,
  components: { Project},
  template: '<Project/>'
})
