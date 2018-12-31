<template>
    <article v-loading="isLoading" class="ov-wrapper">
        <div style="display: flex;border-bottom: 1px solid rgb(222, 222, 222);padding-bottom: 10px;margin-bottom: 10px;font-size: 17px;align-items: flex-end;">
          <p class="ov-title">项目总览</p>
          <div style="width: 49%;display: flex;justify-content: flex-end;">
                <el-button @click="subscribe" size="mini"  icon="el-icon-view">
                  <template v-if="project.pSubed">已</template>关注  
                  <span style="font-weight:bold">{{project.pSubsNum}}</span>
                </el-button>
          </div>
        </div>
        <div style="display:flex">
            <div style="display:flex;width:70%;">
                <article style=";overflow: hidden;cursor: pointer;" :style="{height:height+'px',width:width+'px'}">
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
                <p>模型数：{{project.models.length}}</p>

            </div>    
        </div>        
        <div style="display: flex;border-bottom: 1px solid rgb(222, 222, 222);padding-bottom: 10px;margin-bottom: 10px;font-size: 17px;align-items: flex-end;">
          <p class="ov-title">模型总览</p>
        </div>
        <div style="display:flex;flex-flow:row wrap;">
            <mw-photo  v-for="model in project.models" :key="model.mId" :name="model.mName" 
                :time="model.mCreateTime" :img_url="model.mPreview" height="150px" :redirect="'/model#/'+model.mId"></mw-photo>
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
            isLoading:false,
            dialogVisible:false,
            height:200,
            aspect:'',
            modelName:'',
            project:{
                pId:null,
                pCreateTime:'',
                pName: '',
                pContext:'',
                pPreview:'',
                pSubsNum:null,
                pSubed:false,
                user:{
                    uUsername:''
                },
                models:[]
            },
        }
    },
    computed:{
        width:function(){
            return this.aspect*parseFloat(this.height);
        }
    },
    created: function(){
        this.isLoading =true;
        this.$http.get('/api/project/'+this.$route.params.pId).then(response=>{
            this.project = response.data;

            var img=new Image();
            var obj=this;
            img.src=obj.project.pPreview;
            img.onload=function(){
                obj.aspect=(img.width/img.height);
                obj.isLoading =false;
            }            
        })

    },
    methods:{
        addModel:function(){
            this.$http.post('/api/model',{
                    mName: this.modelName,
                    project:{
                        pId:this.$route.params.pId
                    }
                }).then((response)=>{
                if (response.data){
                    this.dialogVisible = false
                    window.location.href = '/model#/'+response.data
                }
            })
        },
        subscribe(){
            var pId = this.$route.params.pId;
            if(this.project.pSubed){
                this.$http.delete('/api/subscribe/'+pId).then(response=>{
                    this.project.pSubsNum -=1;
                    this.project.pSubed =false;
                })
            }
            else{
                this.$http.post('/api/subscribe/'+pId).then(response=>{
                    this.project.pSubsNum +=1;
                    this.project.pSubed =true;
                })

            }
        }

    },
    components:{mwPhoto,}
}
</script>