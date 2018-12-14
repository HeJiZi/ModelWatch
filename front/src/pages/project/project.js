// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Project from './ProjectOv'
import router from './router'
import ElementUI from 'element-ui'
import VCharts from 'v-charts'
import VueResource from 'vue-resource'

import 'element-ui/lib/theme-chalk/index.css';


Vue.config.productionTip = false
Vue.use(ElementUI) 
Vue.use(VCharts)
Vue.use(VueResource)


/* eslint-disable no-new */
new Vue({
  el: '#project',
  router,
  components: { Project},
  template: '<Project/>',
  render: h => h(Project),
})
