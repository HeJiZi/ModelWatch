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
            <div class="avatar-uploader">
                <label for="test" tabindex="0" class="el-upload el-upload--text">
                    <img v-if="project.pPreview" :src="project.pPreview" class="up-pre-photo" :style="{width:width+'px'}">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    <input @change="upload" id="test" type="file" name="file" class="el-upload__input">
                </label>
            </div>
        </section>
        <section class="pro-input-box">
            <el-button @click="submit" type="primary" style="width:50%;" :disabled="disFlag">Submit</el-button>
        </section>
    </div>
</article>
</template>
<script>
export default {
    data(){
        return{
            project:{

            }
        }
    }
}
</script>
