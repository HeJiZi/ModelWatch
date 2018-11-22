import Vue from 'vue'
import Router from 'vue-router'
import View from './../components/View.vue'
import InterAct from './../components/Interact.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      components: {
        default: View,
        interact: InterAct
      }
    }
  ]
})
