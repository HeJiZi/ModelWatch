import Vue from 'vue'
import Router from 'vue-router'
import UserRegister from './../components/UserRegister.vue'
import UserLogin from './../components/UserLogin.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      meta:{index:0},
      component:UserLogin
    },
    {
      path:'/up',
      meta:{index:1},
      component:UserRegister
    }
  ]
})
