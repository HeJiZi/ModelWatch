<template>
    <article class="ov-wrapper">
        <div style="display: flex;border-bottom: 1px solid rgb(222, 222, 222);padding-bottom: 10px;margin-bottom: 10px;font-size: 17px;align-items: flex-end;">
          <p class="ov-title">项目总览</p>
          <div style="width: 49%;display: flex;justify-content: flex-end;">
              <el-button size="mini"  icon="el-icon-view">关注  <span style="font-weight:bold">35</span></el-button>
          </div>
        </div>
        <div style="display:flex">
            <div style="display:flex;width:70%;">
                <article style=";overflow: hidden;cursor: pointer;" :style="{height:height,width:width}">
                    <img :height="height" :width="width" :src="project.pPreview">
                </article>
                <div id="des" style="margin-left: 20px;">
                    <p style="font-weight: bold;font-size: 18px;">{{project.pName}}</p>
                    <p><i class="el-icon-star-on"></i> {{project.user.uUsername}}</p>
                    <p><i class="el-icon-time"></i> {{project.pCreateTime}}</p>
                    <p style="margin-top:25px;"><i class="el-icon-edit-outline"/> {{project.pContext}}</p>
                </div>
                      
            </div>
            <div style="padding-left:10px;border-left:1px solid rgb(222, 222, 222);height:80px">
                <p>模型数：{{models.length}}</p>

            </div>    
        </div>        
        <div style="display: flex;border-bottom: 1px solid rgb(222, 222, 222);padding-bottom: 10px;margin-bottom: 10px;font-size: 17px;align-items: flex-end;">
          <p class="ov-title">模型总览</p>
        </div>
        <div style="display:flex;flex-flow:row wrap;">
            <mw-photo  v-for="model in models" :key="model.mId" :name="model.mName" 
                :time="model.mCreateTime" :img_url="model.mPreview" height="150px" :redirect="'/model/dis/'+model.mId"></mw-photo>
            <div @click="dialogVisible =true;" class="avatar-uploader">
                <label for="test" tabindex="0" class="el-upload el-upload--text" style="margin-top:0px">
                    <i class="el-icon-plus avatar-uploader-icon"></i>
                </label>
            </div>                
        </div>

        <el-dialog
                title="新建一个模型"
                :visible.sync="dialogVisible"
                width="30%"
                >
            <el-form label-width="80px">
                <el-form-item label="模型名字">
                    <el-input v-model="modelName" placeholder="请输入模型名字"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addModel">确 定</el-button>
            </span>
        </el-dialog>
    
    </article>
</template>
<style>
.ov-title{
    color: rgb(104, 110, 117);
    width:50%;
    margin-bottom:0px;
    font-family: 'PingFang SC';
    font-weight: bold;
    padding: 0px;
}

.ov-wrapper{
    margin-left: 15px;

}
 .avatar-uploader .el-upload {
     border: 1px dashed #d9d9d9;
     border-radius: 6px;
     cursor: pointer;
     position: relative;
     overflow: hidden;
     margin-top: 10px;
 }
 .avatar-uploader .el-upload:hover {
     border-color: #409EFF;
 }
 .avatar-uploader-icon {
     font-size: 28px;
     color: #8c939d;
     width: 135px;
     height: 150px;
     line-height: 150px !important;
     text-align: center;

 }
 #des p{
     margin-top: 0px;
     margin-bottom: 8px;
 }
</style>

<script>
import mwPhoto from '@/WatchComponents/ModelWatchPhoto.vue'
export default {
    data(){
        return{
            dialogVisible:false,
            height:'200px',
            aspect:'',
            modelName:'',
            project:{
                pId:1,
                pCreateTime:'2018-07-01',
                pName: '星火',
                pContext:'星火是一个制作星星与火焰特效模型的项目',
                pPreview:'/static/images/pc.jpeg',
                user:{
                    uUsername:'HeJiZi'
                }
            },
            models:[
                {mId:1,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/static/images/1.png'},
                {mId:2,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/static/images/1.png'},
                {mId:3,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/static/images/1.png'},
                {mId:4,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/static/images/1.png'},
                {mId:5,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/static/images/1.png'},
            ]
        }
    },
    computed:{
        width:function(){
            return this.aspect*parseFloat(this.height) +'px';
        }
    },
    created: function(){
        var img=new Image();
        var obj=this;
        img.src=obj.project.pPreview;
        img.onload=function(){
            obj.aspect=(img.width/img.height);
        }
    },
    methods:{
        addModel:function(){

        },

    },
    components:{mwPhoto,}
}
</script>