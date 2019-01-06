<template>
    <article>
        <section v-loading="dataLoading"  class="tw-wrapper">
            <div class="title">修改项目信息</div>
            <div class="blo" >
                <section >
                    <label for="pro_name">项目名</label>
                    <el-input class="tex" id="pro_name" v-model="project.pName" placeholder="修改项目名"/>
                </section>
                <section >
                    <label for="des">项目描述</label>
                    <el-input type="textarea" placeholder="修改项目描述" v-model="project.pContext">
                    </el-input>
                </section>
                <section >
                    <label>预览图</label>
                    <div v-loading="loading" class="avatar-uploader">
                        <label for="test" tabindex="0" class="el-upload el-upload--text">
                            <img v-if="project.pPreview" :src="project.pPreview" :height="height"  :width="width">
                            <i v-else class="el-icon-plus p-avatar-uploader-icon"></i>
                            <input @change="changePhoto" id="test" type="file" name="file" class="el-upload__input">
                        </label>
                    </div>  
                </section>
                <el-button type="primary" @click="changeData" class="btn blo">确认修改</el-button>
                <!-- <a href="/proData/{pId}"><input type="submit" class="btn" id="btn" value="确认修改" ></a>             -->
            </div>
        </section>
        <section class="tw-wrapper" style="background-color: rgb(236, 163, 163);margin-top:30px;">
            <div class="title" style="color:white">删除</div>
            <section class="blo">
                <el-button type="danger" class="btn" @click="dialogVisible=true">删除项目</el-button>
            </section>
        </section>
        <el-dialog
                class="identify"
                :visible.sync="dialogVisible"
                width="30%"
                >
            <div slot="title" style="color:white;font-weight:bold;">确认要删除项目吗?</div>
            <el-input type="password" v-model="inputPass" placeholder="请输入密码"></el-input>
            <span slot="footer" class="dialog-footer">
                <el-button  type="danger" @click="deleteProject">确 定</el-button>
            </span>
        </el-dialog>        
    </article>
</template>
<style>
.identify .el-dialog__header{
    background-color: #F56C6C;
}
.identify .el-icon-close{
    color: white;
}
.identify .el-dialog__body{
    padding-bottom: 0px;
}
</style>

<script>
import '|css/change.css'

export default {
    data() {
        return{
            dataLoading:false,
            loading:false,
            dialogVisible:false,
            inputPass:null,
            project:{
                pContext:'星火是一个制作星星与火焰特效模型的项目',
                pName:'星火',
            },
            aspect:'',
            height:300,
            file:undefined
        }

    },
    computed:{
        width:function(){
            return this.aspect*parseFloat(this.height);
        }
    },
    methods: {
        changePhoto(event){
            var file=event.target.files[0];
            if(file == undefined) return
            this.loading =true;
            var oFReader = new FileReader();
            this.file=file;

            oFReader.readAsDataURL(file);

            var obj=this;

            oFReader.onloadend=function (OFRevent) {

                var src=OFRevent.target.result;
                obj.project.pPreview=src;

                var img=new Image();
                img.src=src;
                img.onload=function(){
                    obj.loading =false;
                    obj.aspect=(img.width/img.height);
                }
            }
        },
        changeData(){
            var file=this.file;
            var formdata=new FormData();
            formdata.append("file",file);
            formdata.append("project",JSON.stringify(this.project))
            this.$http.post(
                '/api/project/proData',
                // 请求体中要发送给服务端数据
                formdata,
                {
                    processData: false,       //必不可缺
                    contentType: false,
                }
            ).then((response)=>{
                alert("更改成功")
                
            });
        },
        deleteProject(){
            this.$http.post('/api/project/delete',{
                password:this.inputPass,
                pId:this.project.pId

            },{emulateJSON:true}).then((response)=>{
                if(response.data==true){
                    alert('删除成功')
                    window.location.href = '/'
                }
                else{
                    alert('密码输入错误')
                }
              
            })            
        }
    },
    created(){
        this.dataLoading =true;
        this.$http.get('/api/project/data/'+this.$route.params.pId
            ).then((response)=>{
            this.project = response.data
            var img=new Image();
            var obj=this;
            img.src=obj.project.pPreview;
            img.onload=function(){
                obj.aspect=(img.width/img.height);
                obj.dataLoading =false;
            }               
        })
    }   
}
</script>
