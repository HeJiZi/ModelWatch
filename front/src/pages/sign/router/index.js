import Vue from 'vue'
import Router from 'vue-router'
import UserRegister from './../components/UserRegister.vue'
import UserLogin from './../components/UserLogin.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      component:UserLogin
    },
    {
      path:'/up',
      component:UserRegister
    }
  ]
})
