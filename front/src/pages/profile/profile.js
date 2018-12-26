import Vue from 'vue'
import Profile from './Profile.vue'
import router from './router'
import { Menu,MenuItem,Button,Select,DatePicker,Alert,Option,Loading } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Button)
Vue.use(Select)
Vue.use(DatePicker)
Vue.use(Alert)
Vue.use(Option)
Vue.use(Loading)
/* eslint-disable no-new */
new Vue({
  el: '#profile',
  router,
  components: { Profile },
  template: '<Profile/>'
})