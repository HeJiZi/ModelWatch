Vue.component('mw-nav', {
    inheritAttrs: false,
    props: ['user', 'cs'],
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
            console.log("user_quit");
        },
        search: function () {
            console.log("search invoke!");
        },
        create: function () {
            console.log("create project invoke!")
        }
    },
    template:
        `
        <nav class="mw-nav">
            <div class="mw-nav-wrapper">
                <ul>
                    <li style="padding-left: 0px;">
                        <img src="../resources/images/small_logo.png" width="30px" height="30px">
                        <a href="">&nbsp主页</a>
                    </li>

                    <li style="width: 60%;cursor: default">
                        <input @keydown.enter="search" type="search" placeholder="搜索" class="searchbox">
                    </li>
                    <li @mouseenter="ava_over" @mouseleave="ava_out"
                         :class="cs.ava_class" style="padding: 0;">
                        <div class="nav_avater ava-trans" >
                            <img v-bind:src="user.avater">
                        </div>
                        <div class="mw-nav-menu pf_vis" :style="cs.menu_style" >
                            <div><span> {{user.name}}</span></div>
                            <div class="mw-profile">
                                <div title="项目数">
                                    <div class="title">项目数:</div>
                                    <span>{{user.project_num}}</span>
                                </div>
                                <div title="模型数">
                                    <div class="title">模型数：</div>
                                    <span>{{user.model_num}}</span>
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
                </ul>
            </div>
        </nav>
    `
})
Vue.component('mw-title', {
    props: ['title', 'url'],
    data: function () {
        return {
            color: "black",
        }
    },
    template:
        `
    <div style="height: 50px;display: flex;flex-flow: row wrap;align-items: center;justify-content: center;">
        <a :href="url" style="font-size: 30px;font-weight: bold;font-weight:bold;font-family: 'Times New Roman', Times, serif;
        text-decoration: none;" @mouseenter="color='gray'" @mouseleave="color='black'" :style="{color: color}">
            {{title}}
        </a>
    </div>
    `
})
Vue.component('mw-photo', {
    inheritAttrs: false,
    props: ['height','name','time','img_url','redirect'],
    data: function () {
        return {
            transform: 'translateY(0px)',
            aspect:0,
        }
    },
    computed:{
        width:function(){
            return this.aspect*parseFloat(this.height);
        }
    },
    methods:{
        jump:function(){
            var obj=this;
            window.open(this.redirect);
        }
    },
    created:function(){
        var img=new Image();
        var obj=this;
        img.src=obj.img_url;
        img.onload=function(){
            obj.aspect=(img.width/img.height);
        }
    },
    template:
        `
    <article style="margin-right: 10px;margin-bottom: 10px;overflow: hidden;cursor: pointer;" 
        @mouseenter="transform='translateY(-40px)'" @mouseleave="transform='translateY(0px)'" 
        @click="jump" :style="{height:height}">
        <img :height="height" :width="width" :src="img_url">
        <div :style="{transform: transform}" style="width: 100%;height: 35px;background: rgba(0, 0, 0, 0.253);color:white;
        position: relative;z-index: 1;display: flex;align-items: center;font-family: fantasy; transition: transform ease 0.5s;">
            <span style="color: white; font-weight:bold;font-family:inherit; width:30%">&nbsp&nbsp{{name}}</span>
            <div style="display: flex;align-items: center;width:70%;flex-direction: row-reverse;">
                <div style="margin-right:10px">{{time}} </div>
                <div style="background-image: url(../resources/images/icons.png);background-position: -344px -407px;background-repeat: no-repeat;
                    height:100%;height:18px;width: 20px;"></div>
            </div>
        </div>
    </article>    
    `
})

const vue = new Vue({
    el: '#nav',
    data: {
        user: {
            avater: "../resources/images/temp.jpeg",
            name: "HeJiZi",
            model_num: "102",
            project_num: "11",
            person_data_url: "",
            person_zone_url: "",
            histrory_url: "",
            message_url: "",
            crete_project_url: "",
        },
        cs: {
            da_class: "",
            his_class: "",
            mes_class: "",
            ava_class: "na_unactive",
            menu_style: {
                opacity: "0",
            }
        },
        isActive:false,
    },

})