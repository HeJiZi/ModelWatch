<template>
<div style=" background: rgb(241, 241, 241);">
    <div class="wrapper" >
        <div class="content">
            <section class="cover">
                <div class="wrapper">
                    <div id="avater">
                        <img :src="user.uAvater" />
                    </div>
                    <section id="simple_data">
                        <h1>{{user.uUsername}}</h1>
                        <p>{{user.uSignature}}</p>
                    </section>
                </div>
            </section>
            <el-menu default-active="projects"  mode="horizontal" @select="handleSelect">
            <el-menu-item index="projects">
                <i class="el-icon-menu"></i>
                <span slot="title">我的项目</span>                
            </el-menu-item>
            <el-menu-item index="subScribes">
                <i class="el-icon-view"></i>
                <span slot="title">我的关注</span> 
            </el-menu-item>
            <el-menu-item index="mark">
                <i class="el-icon-star-off"></i>
                <span slot="title">我的收藏</span> 
            </el-menu-item>
            </el-menu>
            <section class="preview">
                <div class="pv_wrapper">
                        <div class="pj-box" >
                            <template v-if="selectedIndex=='projects'">
                                <mw-photo  v-for="project in projects" :key="project.pId" :name="project.pName" :time="project.pCreateTime" 
                                :img_url="project.pPreview" height="250px" :redirect="'/project#/'+project.pId"/>
                                
                            </template>
                            <template v-else-if="selectedIndex=='subScribes'">
                                <mw-photo v-for="subpro in subPros" :key="subpro.pId" :name="subpro.pName" :time="subpro.pCreateTime" 
                                :img_url="subpro.pPreview" height="250px" :redirect="'/project#/'+subpro.pId"/>
                            </template>
                            <template v-else-if="selectedIndex=='mark'">
                                <mw-photo v-for="model in markModels" :key="model.mId" :name="model.mName" :time="model.mCreateTime" 
                                :img_url="model.mPreview" height="150px" :redirect="'/model#/'+model.mId"/>
                            </template>
                        </div>
                </div>
            </section>
        </div>
    </div>
</div>
</template>
<script>
import '|css/pesonnalZone.css'
import mwPhoto from '@/WatchComponents/ModelWatchPhoto.vue'
export default {
    data(){
        return{
            user:{
                uAvater:'/resources/images/temp.jpeg',
                uUsername:'HeJiZi',
                uSignature:'啦啦啦'
            },
            projects:[],
            subPros:[],
            markModels:[
                {mId:1,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
                {mId:2,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
                {mId:3,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
                {mId:4,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
                {mId:5,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},

            ],
            selectedIndex:'projects',
        }
    },
    created(){
        var id=this.$route.params.uId;
        var obj =this;
        this.$http.get('/api/zone/data/'+id).then((response)=>{
            obj.user=response.data
        })
        this.$http.get('/api/zone/JoinPros/'+id).then((response) => {
            obj.projects=response.data;
        }, (response) => {
            // 响应错误回调
        });
        this.$http.get('/api/zone/SubPros/'+id).then((response) => {
            obj.subPros=response.data;
        }, (response) => {
            // 响应错误回调
        });
        this.$http.get('/api/zone/MarkModels/'+id).then((response) => {

            obj.markModels=response.data;
        }, (response) => {
            // 响应错误回调
        });
    },
    methods:{
        handleSelect(key,keyPath){
            this.selectedIndex = key;
        }
    },
    components:{mwPhoto},
}
</script>


