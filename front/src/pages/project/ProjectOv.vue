<template>
  <div id="project">
    <mw-nav id="nav"></mw-nav>
    <article style="display:flex;max-width:1280px;min-width: 800px;margin: 0 auto;">
      <div style="width:15%;">
        <el-menu
        default-active="/"
        :router="true"
        class="el-menu-vertical-demo">
        <el-menu-item index="/">
            <i class="el-icon-location"></i>
            <span slot="title">项目总览</span>
        </el-menu-item>
        <el-menu-item index="/log">
          <i class="el-icon-menu"></i>
          <span slot="title">日志管理</span>
        </el-menu-item>
        <el-menu-item index="/teamworker">
          <i class="el-icon-document"></i>
          <span slot="title">协作者管理</span>
        </el-menu-item>
        <el-menu-item index="/profile">
          <i class="el-icon-setting"></i>
          <span slot="title">资料管理</span>
        </el-menu-item>
        <el-menu-item index="/data">
          <i class="el-icon-setting"></i>
          <span slot="title">数据统计</span>
        </el-menu-item>
      </el-menu>
      </div>
      <div>
        <router-view/>
      </div>
      
    </article>

  </div>
</template>

<script>
import mwNav from '@/WatchComponents/ModelWatchNav.vue'

export default {
  name: 'Project',
  components:{
    mwNav
  },

}
</script>

<style>

</style>
