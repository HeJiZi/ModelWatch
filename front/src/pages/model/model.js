// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Model from './Model.vue'
import router from './router'
import { Button,MenuItem,Menu,Tree,Input,Pagination,Loading,Tabs,TabPane,Row } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';


Vue.config.productionTip = false
Vue.use(Button)
Vue.use(MenuItem)
Vue.use(Menu)
Vue.use(Tree)
Vue.use(Input)
Vue.use(Pagination)
Vue.use(Loading)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Row)
/* eslint-disable no-new */
new Vue({
  data(){
    return{
      mesh:null
    }
  },
  el: '#model',
  router,
  components: { Model },
  template: '<Model/>'
})
