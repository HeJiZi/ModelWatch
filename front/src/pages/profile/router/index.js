import Vue from 'vue'
import Router from 'vue-router'
import Add from './../components/Add.vue'
import Profile from './../components/Profile.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/:uId',
      name: 'Profile',
      component: Profile
    },
    {
        path: '/:uId/add',
        name: 'Add',
        component: Add,
    },

  ]
})
