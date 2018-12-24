import Vue from 'vue'
import Router from 'vue-router'
import Overview from './../components/Overview.vue'
import Log from './../components/LogManager.vue'
import TeamWorker from './../components/TeamWorkers.vue'
import Profile from './../components/Profile.vue'
import Data from './../components/Data.vue'


Vue.use(Router)


export default new Router({
  routes: [
    {
      path: '/:pId',
      name: 'Overview',
      component: Overview
    },
    {
        path: '/:pId/log',
        name: 'Log',
        component: Log
    },
    {
        path: '/:pId/teamworker',
        name: 'TeamWorker',
        component: TeamWorker
    },
    {
        path: '/:pId/profile',
        name: 'Profile',
        component: Profile
    },
    {
        path: '/:pId/data',
        name: 'Data',
        component: Data
    }
  ]
})
