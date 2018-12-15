<template>
    <!-- 回复的输入面板 -->
    <div v-if="repId == needRepId && needRepType == keyType && comId == repCom">
        <div style="display: flex; padding-top: 20px; padding-bottom: 30px; width: 100%;" >
            <!-- 头像 -->
            <div class="tempAvater" style="margin-right: 10px; margin-top: 0px">
                <img :src="user.uAvater" width="70px" height="70px">
            </div>

            <div style="width:90%;">
                <!-- 顶部提示信息 -->
                <!-- <p style="height: 25px;line-height: 25px;color:#b9b9b9">{{inputTip}}</p> -->

                <div style=" display: flex;">
                    <div style="width: 90%">
                        <e-input ref="editor" :inputTip="inputTip" v-on:newContentListener="returnNewContent"></e-input>
                    </div>
                    <!-- 发表评论的按钮 -->
                    <div style="margin-left: 20px; width: 75px;">
                        <el-button style="padding: 4px; height: 75px; width:75px; font-size: 13pt; line-height:24px;" 
                            type="primary"  @click="activeReply">
                            发&nbsp;&nbsp;表<br>评&nbsp;&nbsp;论
                        </el-button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>
<script>
import E from 'wangeditor'
import eInput from './Input.vue'

export default {
    props:['user', 'repId', 'needRepId', 'keyType', 'needRepType', 'comId', 'repCom', 'inputTip'],

    methods: {
        
        activeReply() {
            this.$refs.editor.getContent();
        },

        // 返回新的评论、回复信息
        returnNewContent(newContent) {
            alert(newContent);

            var url = window.location.href;
            var id = url.substr(url.lastIndexOf('/') + 1, url.length);
            this.$http.post('/model/comment',{
                comContent:newContent,
                comUId:this.user.uId,
                comMId:id,
            }).then((response) => {
                if(response.data == true) alert("评论成功，请等待管理员审核");
                else alert("评论失败，存在异常操纵")
            })
        }

    },
    inheritAttrs: false,
    components: { eInput },
}
</script>

<style>

</style>
