<template>
    <div>
        <mw-nav/>
        <!-- <div style="background:#e6e6e6;margin:0px;padding:0px;height:1px;"/> -->
        <div style="max-width:1280px;min-width:800px;margin:5px auto;display:flex;">
            <nav style="width:15%;margin-right:5px;">
                <el-menu :default-active="$route.path" :router="true" >
                    <el-menu-item :index="'/'+$route.params.uId">
                        <i class="el-icon-document"></i>
                        <span>个人资料</span>
                    </el-menu-item>
                    <el-menu-item :index="'/'+$route.params.uId+'/add'">
                        <i class="el-icon-plus"></i>
                        <span >创建项目</span>
                    </el-menu-item>
                </el-menu>
            </nav>
            <router-view style="width:80%;background:white;"/>
        </div>
    </div>
</template>
<style>
body{
    background: rgb(241, 241, 241);
}
</style>

<script>
import mwNav from '@/WatchComponents/ModelWatchNav.vue'

export default {
    components:{mwNav}
}
</script>
