import Vue from 'vue'
import Router from 'vue-router'
import Overview from './../components/Overview.vue'
import Log from './../components/LogManager.vue'
import TeamWorker from './../components/TeamWorkers.vue'
import Profile from './../components/Profile.vue'
import Data from './../components/Data.vue'
import Inv from './../components/Inv.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Overview',
      component: Overview
    },
    {
        path: '/log',
        name: 'Log',
        component: Log
    },
    {
        path: '/teamworker',
        name: 'TeamWorker',
        component: TeamWorker
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile
    },
    {
        path: '/data',
        name: 'Data',
        component: Data
    },
    {
        path: '/inv',
        name: 'Inv',
        component:Inv
    },
  ]
})
