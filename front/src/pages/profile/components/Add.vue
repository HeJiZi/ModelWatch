<template>
<article id="profile" style="padding-bottom:10px;" >
    <section class="profile-top">
        <p style="margin-left:20px;font-weight:bold;font-size: 1.3em;">新建项目</p>
    </section>
    <div style="width: 100%;display: flex;flex-flow:row wrap;justify-content: center;  ">
        <section class="pro-input-box">
            <label for="name">名字</label>
            <input v-model="project.pName" @keyup="varifyCanSubmit"
                   id='name' name="name" placeholder="name" type="text">
        </section>

        <section class="pro-input-box">
            <label for="discription">简介</label>
            <textarea v-model="project.pContext" @keyup="varifyCanSubmit" id="discription"></textarea>
        </section>
        <section class="pro-input-box">
            <label>预览图</label>
            <div  class="avatar-uploader">
                <label  v-loading="loading" for="test" tabindex="0" class="el-upload el-upload--text">
                    <img v-if="project.pPreview" :src="project.pPreview" class="up-pre-photo" :style="{width:width+'px'}">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    <input @change="upload" id="test" type="file" name="file" class="el-upload__input">
                </label>
            </div>
        </section>
        <section class="pro-input-box" style="margin-top:20px;">
            <el-button v-loading="buttonLoading" @click="submit"  type="primary" style="width:66%;" :disabled="disFlag&&!buttonLoading">创建项目</el-button>
        </section>
    </div>
</article>
</template>
<script>
import '|css/main.css'
export default {
    data(){
        return{
            project:{
                pPreview:'',
                pContext:'',
            },
            file:'',
            disFlag:true,
            aspect:'',
            loading: false,
            buttonLoading:false,
        }
    },
    computed:{
        width:function(){
            return this.aspect*300.0;
        }
    },
    methods:{
        varifyCanSubmit:function(){
            if(this.project.pName!=''&&this.project.pContext!=''&&this.file!='')
                this.disFlag=false;
            else
                this.disFlag=true;
        },
        upload:function (event) {
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
            this.varifyCanSubmit();
        },
        submit:function () {
            this.buttonLoading = true;
            var formdata=new FormData();
            formdata.append("file",this.file);
            formdata.append("project",JSON.stringify(this.project))
            this.$http.post(
                '/api/project',
                // 请求体中要发送给服务端数据
                formdata,
                {
                    processData: false,       //必不可缺
                    contentType: false,
                }
            ).then((response)=>{
                    //页面跳转
                var pId =response.data;
                this.buttonLoading =false;
                if(pId == 0){
                    alert("添加失败")
                }
                else{
                    alert("添加成功")
                    window.location.href='/project#/'+pId;
                }
            },(response)=>{
                alert("服务器异常，请稍后再试")
                this.buttonLoading =false;
            });

        }
    }
}
</script>
