import Vue from 'vue'
import Router from 'vue-router'
import Inv from './../Inv.vue'


Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/:pId/:myName',
      name: 'Inv',
      component: Inv
    }
  ]
})
