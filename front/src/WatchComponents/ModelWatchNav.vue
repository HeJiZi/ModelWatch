<template>
     <nav class="mw-nav">
        <div class="mw-nav-wrapper">
            <ul>
                <li style="padding-left: 0px;">
                    <img src="/resources/images/small_logo.png" width="30px" height="30px">
                    <a href="/">&nbsp;主页</a>
                </li>

                <li style="width: 60%;cursor: default">
                    <input @keydown.enter="search" type="search" placeholder="搜索" class="searchbox">
                </li>
                <template v-if="user.uId">
                    <li @mouseenter="ava_over" @mouseleave="ava_out"
                            :class="cs.ava_class" style="padding: 0;">
                        <div @click="jump_to_zone" class="nav_avater ava-trans" >
                            <img v-bind:src="ava_url">
                        </div>
                        <div class="mw-nav-menu pf_vis" :style="cs.menu_style" >
                            <div><span> {{user.uName}}</span></div>
                            <div class="mw-profile">
                                <div title="项目数">
                                    <div class="title">项目数:</div>
                                    <span>{{user.uProjectNum}}</span>
                                </div>
                                <div title="模型数">
                                    <div class="title">模型数：</div>
                                    <span>{{user.uModelNum}}</span>
                                </div>
                                <div class="footer">
                                    <div class="quit_button">退出</div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li @mouseenter="cs.da_class = 'active'" @mouseleave="cs.da_class = ''"
                            :class="cs.da_class">
                        <a v-bind:href="profile_url">个人资料</a>
                    </li>
                    <li @mouseenter="cs.his_class = 'active'" @mouseleave="cs.his_class = ''"
                        :class="cs.his_class">
                        <a v-bind:href="user.history_url">浏览历史</a>
                    </li>
                    <li @mouseenter="cs.mes_class = 'active'" @mouseleave="cs.mes_class = ''"
                        :class="cs.mes_class">
                        <a :href="user.message_url">我的消息</a>
                    </li>
                    <li class="createBut" @click="jump_to_create">创建项目</li>
                </template>
                <template v-else>
                    <div style="width: 30%;height: 100%;">
                        <a class="mw-nav-menu-sign" href="/sign">登录/注册</a>
                    </div>
                </template>
            </ul>
        </div>
    </nav>
</template>
<script>
import '|css/watchUI.css'
import VueResource from 'vue-resource'
import Vue from 'vue'
Vue.use(VueResource)

export default {
    name: 'mw-nav',
    inheritAttrs: false,
    created(){
        this.$http.get('/api/user').then((response)=>{
            var data=response.data
            this.user =data;
        })
    },
    data(){
        return{
            cs:{
                ava_class:'',
                da_class:'',
                mes_class:'',
                menu_style:{
                    opacity:0
                }
            },
            user:{},
        }
    },
    computed:{
        ava_url(){
            return this.user.uAvater?this.user.uAvater:"/resources/images/defalut.png";
        },
        creat_url(){
            return '/user#/'+this.user.uId+'/add'
        },
        zone_url(){
            return '/#/zone/'+this.user.uId
        },
        profile_url(){
            return '/user#/'+this.user.uId
        }
    },
    methods: {
        ava_over: function () {
            var obj = this;
            obj.cs.ava_class = "na_active";
            setTimeout(
                function () { obj.cs.menu_style.opacity = "1"; },
                50
            )
        },
        ava_out: function () {
            this.cs.ava_class = "na_unactive";
            this.cs.menu_style.opacity = "0";
        },
        quit: function () {
            window.open('/sign')
        },
        search: function () {
            console.log("search invoke!");
        },
        jump_to_zone(){
            window.location.href=this.zone_url
        },
        jump_to_create(){
            window.open(this.creat_url);
        }
    },
}
</script>

