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

    data() {
      return {
      }
    },

    methods: {
        
        activeReply() {
            this.$refs.editor.getContent();
        },

        // 返回新的评论、回复信息
        returnNewContent(newContent) {

            var url = window.location.href;
            var mId = url.substr(url.lastIndexOf('/') + 1, url.length);

            // 发送评论
            if (this.repId == '0') {

                this.$http.post('/api/comment/add', {
                  comContent: newContent,
                  comUId: this.user.uId,
                  comMId: mId,
                }).then(
                  function (response) {
                    if (response.data) {
                      alert("评论成功");
                      this.$refs.editor.$refs.textplace.getElementsByTagName("p").item(0).innerHTML = "";
                      this.$emit("showNewComment", 0);
                    } else {
                      alert("评论失败");
                    }
                  }
                );
            }
            else {
                var repRId;
                if (this.keyType != "0") {
                  repRId = this.needRepId;  // this.needRepId: 此处指评论的id
                } else {
                  repRId = 0;
                  // repRId = this.needRepId;
                }
                console.log(newContent, repRId, this.comId, this.user.uId, this.keyType)
                this.$http.post('/api/reply/add', {
                    repContent: newContent,
                    repRId: repRId,
                    repComId: this.comId,
                    repUId: this.user.uId,
                    repType: this.keyType,
                }).then(
                    function (response) {
                      if (response.data) {
                        alert("评论成功");
                        this.$refs.editor.$refs.textplace.getElementsByTagName("p").item(0).innerHTML = "";
                        this.$emit("closeCancelTip", repRId, this.keyType);
                      } else {
                        alert("评论失败");
                      }
                    }
                );
            }
        }

    },
    inheritAttrs: false,
    components: { eInput },
}
</script>

<style>

</style>
