import Vue from 'vue'
import Router from 'vue-router'
import View from './../components/View.vue'
import InterAct from './../components/Interact.vue'
import Display from './../components/Display.vue'
import Editor from './../components/Editor.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/:mId',
      component: Display,
      children: [
        {
          path:'/',
          components:{
            default: View,
            interact: InterAct
          }
        }
      ]
    },
    {
      path: '/:mId/editor',
      component: Editor,
    }
  ]
})
