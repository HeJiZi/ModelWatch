// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Inv from './Inv.vue'
import { Button } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false
Vue.use(Button)
/* eslint-disable no-new */
new Vue({
  el: '#inv',
  components: { Inv },
  template: '<Inv/>'
})
