<template>
    <article>
        <section  class="tw-wrapper">
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
                    <div class="avatar-uploader">
                        <label for="test" tabindex="0" class="el-upload el-upload--text">
                            <img v-if="project.pPreview" :src="project.pPreview" class="up-pre-photo" :style="{width:width+'px'}">
                            <i v-else class="el-icon-plus p-avatar-uploader-icon"></i>
                            <input @change="upload" id="test" type="file" name="file" class="el-upload__input">
                        </label>
                    </div>  
                </section>
                <el-button type="primary" class="btn blo">确认修改</el-button>
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
            <el-input v-model="inputPass" placeholder="请输入密码"></el-input>
            <span slot="footer" class="dialog-footer">
                <el-button  type="danger" @click="console.log(111)">确 定</el-button>
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
import '@/../static/css/change.css'
var image = '';
function upload(file) {
    if (!file.files || !file.files[0]) {c
        return;
    }
    var reader = new FileReader();

    reader.onload = function (event) {
        document.getElementById('image').src = event.target.result;
        var image=new Image();
        image.src = event.target.result;
        image.onload=function () {
            console.log(image.width/image.height)
            $('#image').attr('style','width:'+300*(image.width/image.height)+'px')
        }

    }
    reader.readAsDataURL(file.files[0]);
}

export default {
  data() {
    return{
        pid: null,
        dialogVisible:false,
        inputPass:null,
        project:{
            pContext:'星火是一个制作星星与火焰特效模型的项目',
            pName:'星火',
        }
    }

    },
    methods: {
       
        upload(file){

        }
    },
    created(){
        // var url=window.location.href;
        // var pid=url.substr(url.lastIndexOf('/')+1,url.length);
        // this.pid = pid
        // this.$http.get('/invitation/'+pid
        //     ).then((response)=>{
        //     this.invitedUser = response.data
        //     for (i of this.invitedUser){
        //         this.dynamicTags.push(i.uUsername)
        //     }
        // })
    }   
}
</script>