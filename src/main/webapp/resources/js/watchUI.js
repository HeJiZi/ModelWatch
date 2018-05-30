Vue.component('mw-nav', {
    inheritAttrs:false,
    data: function () {
        return {
            count: 0
        }
    },
    template: ' \
    <nav class="mw-nav">\
    <div class="mw-nav-wrapper">\
        <ul>\
            <li style="padding-left: 0px;">\
                <img src="../resources/images/small_logo.png" width="30px" height="30px">\
                <a href="">&nbsp主页</a>\
            </li>\

            <li style="width: 60%;cursor: default">\
                <input v-on:keydown.enter="search" type="search" placeholder="搜索" class="searchbox">\
            </li>\
            <li v-on:mouseenter="ava_over" v-on:mouseleave="ava_out"\
                 v-bind:class="ava_class" style="padding: 0;">\
                <div class="nav_avater ava-trans" >\
                    <img v-bind:src="user.avater">\
                </div>\
                <div class="mw-nav-menu pf_vis" v-bind:style="menu_style" >\
                    <div><span> {{user.name}}</span></div>\
                    <div class="mw-profile">\
                        <div title="项目数">\
                            <div class="title">项目数:</div>\
                            <span>{{user.project_num}}</span>\
                        </div>\
                        <div title="模型数">\
                            <div class="title">模型数：</div>\
                            <span>{{user.model_num}}</span>\
                        </div>\
                        <div class="footer">\
                            <div class="quit_button">退出</div>\
                        </div>\
                    </div>\
                </div>\
            </li>\
            <li v-on:mouseenter="data_over" v-on:mouseleave="data_out"\
                 v-bind:class="da_class">\
                <a v-bind:href="user.person_data_url">个人资料</a>\
            </li>\
            <li v-on:mouseenter="his_over" v-on:mouseleave="his_out"\
                v-bind:class="his_class">\
                <a v-bind:href="user.history_url">浏览历史</a>\
            </li>\
            <li v-on:mouseenter="mes_over" v-on:mouseleave="mes_out"\
                v-bind:class="mes_class">\
                <a v-bind:href="user.message_url">我的消息</a>\
            </li>\
            <li class="createBut" v-on:click="create">创建项目</li>\
        </ul>\
    </div>\
</nav>'
})
const vue=new Vue({
    el:'#nav',
    data:{
        user:{
            avater:"../resources/images/temp.jpeg",
            name:"HeJiZi",
            model_num:"102",
            project_num:"11",
            person_data_url:"",
            person_zone_url:"",
            histrory_url:"",
            message_url:"",
            crete_project_url:"",
        },
        da_class:"",
        his_class:"",
        mes_class:"",
        ava_class:"na_unactive",
        menu_style:{
            opacity:"0",
        }
    },
    methods:{
        data_over:function(){
            this.da_class="active";
        },
        data_out:function(){
            this.da_class="";
        },
        his_over:function(){
            this.his_class="active";
        },
        his_out:function(){
            this.his_class=""
        },
        mes_over:function(){
            this.mes_class="active";
        },
        mes_out:function(){
            this.mes_class="";
        },
        ava_over:function(){
            var obj=this;
            obj.ava_class="na_active";
            setTimeout(
                function(){obj.menu_style.opacity="1";},
                50
            )
        },
        ava_out:function(){
            this.ava_class="na_unactive";
            this.menu_style.opacity="0";
        },
        quit:function(){
            console.log("user_quit");
        },
        search:function(){
            console.log("search invoke!");
        },
        create:function(){
            console.log("create project invoke!")
        }
    }
})