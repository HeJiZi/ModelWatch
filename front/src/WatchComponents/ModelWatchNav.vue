<template>
     <nav class="mw-nav">
        <div class="mw-nav-wrapper">
            <ul>
                <li style="padding-left: 0px;">
                    <img src="/static/images/small_logo.png" width="30px" height="30px">
                    <a href="/">&nbsp;主页</a>
                </li>

                <li style="width: 60%;cursor: default">
                    <input @keydown.enter="search" type="search" placeholder="搜索" class="searchbox">
                </li>
                <template v-if="ok">
                    <li @mouseenter="ava_over" @mouseleave="ava_out"
                            :class="cs.ava_class" style="padding: 0;">
                        <div @click="jumptoZone" class="nav_avater ava-trans" >
                            <img v-bind:src="user.uAvater">
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
                    <li @mouseenter="data_over" @mouseleave="data_out"
                            :class="cs.da_class">
                        <a v-bind:href="user.person_data_url">个人资料</a>
                    </li>
                    <li @mouseenter="his_over" @mouseleave="his_out"
                        :class="cs.his_class">
                        <a v-bind:href="user.history_url">浏览历史</a>
                    </li>
                    <li @mouseenter="mes_over" @mouseleave="mes_out"
                        :class="cs.mes_class">
                        <a :href="user.message_url">我的消息</a>
                    </li>
                    <li class="createBut" @click="create">创建项目</li>
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
import '@/../static/css/watchUI.css'

export default {
    name: 'mw-nav',
    inheritAttrs: false,
    props: ['user', 'cs','ok'],
    methods: {
        data_over: function () {
            this.cs.da_class = "active";
        },
        data_out: function () {
            this.cs.da_class = "";
        },
        his_over: function () {
            this.cs.his_class = "active";
        },
        his_out: function () {
            this.cs.his_class = ""
        },
        mes_over: function () {
            this.cs.mes_class = "active";
        },
        mes_out: function () {
            this.cs.mes_class = "";
        },
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
        create: function () {
            window.open(this.user.create_project_url);
        },
        jumptoZone:function(){
            window.open(this.user.person_zone_url);
        }
    },
}
</script>

