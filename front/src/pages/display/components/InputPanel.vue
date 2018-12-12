<template>
    <!-- 回复的输入面板 -->
    <div v-if="repId == needRepId && needRepType == keyType && comId == repCom">
        <div style="display: flex; padding-top: 20px; width: 100%;" >
            <!-- 头像 -->
            <div class="tempAvater" >
                <img :src="avater" width="60px" height="60px">
            </div>

            <div style="width:80%;">
                <p style="height: 25px;line-height: 25px;color:#b9b9b9">{{message}}</p>
                <!-- <mw-input :newComments="newComments"></mw-input> -->
                <e-input ref="editor" v-on:newContentListener="returnNewContent"></e-input>
            </div>

            <!-- 发表评论的按钮 -->
            <div style="margin-left: 20px; width: 80px; display:flex; align-items:center;">
                <el-button style="padding: 4px; height: 75px; width: 100%; font-size: 13pt; line-height:24px;" 
                    type="primary"  @click="activeReply">
                    发&nbsp;&nbsp;表<br>评&nbsp;&nbsp;论
                </el-button>
            </div>
        </div>
    </div>

</template>
<script>
import E from 'wangeditor'
import eInput from './Input.vue'

export default {
    props:['avater', 'repId', 'needRepId', 'keyType', 'needRepType', 'comId', 'repCom', 'message','newComments'],

    methods: {
        activeReply() {
            this.$refs.editor.getContent();
        },

        // 返回新的评论、回复信息
        returnNewContent(data) {
            alert(data)
        }

    },
    inheritAttrs: false,
    components: { eInput },
}
</script>

<style>

</style>
