import Vue from 'vue'
import Router from 'vue-router'
import IndexPage from './../components/Index.vue'
import Zone from './../components/Zone.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: IndexPage
    },
    {
        path: '/zone/:uId',
        name: 'Zone',
        component: Zone,
    },

  ]
})
