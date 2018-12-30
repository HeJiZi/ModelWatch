<template>
    <div style="width:1278px;">
        <mw-nav/>
        <el-card class="box-card">
            <div slot="header" >
                <span style="padding-left:170px;"><i class="el-icon-message"></i><span style="color:rgb(137, 150, 173);padding-left:10px;">{{invitation.uUsername}}的邀请函</span></span>
            </div>
            <div class="inv">
                <el-row style="font-size:20px;margin-bottom:30px;padding-top:20px;font-family:'Times New Roman', Times, serif;">邀请信息</el-row>
                <el-row>
                    <img :src='invitation.uAvater' class="figure">
                    <i class="el-icon-plus" style="margin:0px 30px"></i>
                    <img :src='invitation.mAvater' class="figure" >
                </el-row>    
                <el-row>
                    <div style="padding-top:40px;padding-bottom:20px;">
                    <span class="tag"><a href="">@{{invitation.uUsername}}</a>邀请你在{{invitation.pName}}<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目上进行协作</span>
                    </div>
                </el-row>
                <el-row>
                    <div style="padding-top:20px;padding-bottom:30px;border-bottom:1px solid rgb(233, 237, 245); ">
                        <el-button type="primary" plain @click="accept">接受邀请</el-button>
                        <el-button type="info" plain @click="decline">拒绝邀请</el-button>
                    </div>
                </el-row>
                <el-row>
                    <div style="padding:30px 50px 55px 400px;font-size:18px;text-align:left;">
                        您可以接受或拒绝这个邀请。您也可以去查阅<a href="">{{invitation.pName}}</a><br>资料库或访问以了解更多...
                    </div>
                </el-row>
            </div>
        </el-card>           
    </div>
 

</template>

<style>
    .box-card {
        width: 100%;
    }
    .inv{
        position: relative;
        align-content:center;
        text-align: center;
        background-color:rgb(242, 246, 252);
    }
    .figure{
        border-color:rgb(175, 198, 241);
        border-width: 3px;
        border-style: solid;
        border-radius: 5px;
        width: 80px;
        height: 80px;
    }
    .el-card__header{
        background-color:rgb(233, 237, 245); 
    }
    .el-card__body{
        padding:0px;
    }
    .tag{
        font-family: 'Times New Roman', Times, serif;
        font-size: 25px;
    }
    img:hover{ transform:scale(1.02,1.02)}
    a:link{
        text-decoration:none;
    }
    a:visited{
        text-decoration:none;
    }
    a:hover{text-decoration:underline;}
    a:active{text-decoration:none;}
</style>

<script>
import mwNav from '@/WatchComponents/ModelWatchNav.vue'
export default {
    data(){
        return{
            pid:'',
            myName:'',
            invitation:[]
        }
    },
    methods:{
        accept(){
            this.$http.get('/api/invitation/updateState/'+this.pid+'/'+this.myName).then((Response)=>{
                    if(Response.data){
                        alert("您已接受邀请!");
                    }
                    else{alert("邀请不存在！");}
            });
        },
        decline(){
            this.$http.get('/api/invitation/deleteWaitState/'+this.pid+'/'+this.myName).then((Response)=>{
                    if(Response.data==1){alert("您已拒绝!");}
                    else{alert("页面出错");}
            });
        }
    },
    created(){
        this.pid=this.$route.params.pId;
        this.myName=this.$route.params.myName;
        this.$http.get('/api/invitation/findMessage/'+this.pid+'/'+this.myName).then((Response)=>{
                   this.invitation=Response.data;
        });
    },
    components:{mwNav}
}

</script>

