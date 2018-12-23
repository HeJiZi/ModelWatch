<template>
  <div id="project">
    <mw-nav id="nav"></mw-nav>
    <div style="background:#e6e6e6;margin:0px;padding:0px;height:1px;"></div>
    <article style="display:flex;max-width:1280px;min-width: 800px;margin: 0 auto;">
      <div style="width:15%;">
        <el-menu
        :default-active="defaultActive"
        :router="true"
        class="el-menu-vertical-demo">
        <el-menu-item :index="'/'+$route.params.pId">
            <i class="el-icon-location"></i>
            <span slot="title">项目总览</span>
        </el-menu-item>
        <el-menu-item :index="'/'+$route.params.pId+'/log'">
          <i class="el-icon-menu"></i>
          <span slot="title">日志管理</span>
        </el-menu-item>
        <el-menu-item :index="'/'+$route.params.pId+'/teamworker'">
          <i class="el-icon-document"></i>
          <span slot="title">协作者管理</span>
        </el-menu-item>
        <el-menu-item :index="'/'+$route.params.pId+'/profile'">
          <i class="el-icon-setting"></i>
          <span slot="title">项目设置</span>
        </el-menu-item>
        <el-menu-item :index="'/'+$route.params.pId+'/data' ">
          <i class="el-icon-setting"></i>
          <span slot="title">数据统计</span>
        </el-menu-item>
        <el-menu-item index="/inv">
          <i class="el-icon-setting"></i>
          <span slot="title">邀请页面</span>
        </el-menu-item>
      </el-menu>
      </div>
      <div style="width:85%">
        <router-view/>
      </div>
      
    </article>

  </div>
</template>
<style>
body{
  width: 100%;
  overflow-y: scroll;
}
</style>

<script>
import mwNav from '@/WatchComponents/ModelWatchNav.vue'

export default {
  name: 'Project',
  computed: {
        defaultActive() {
            return this.$route.path;
        }
  },
  components:{
    mwNav
  },

}
</script>

<style>

</style>
