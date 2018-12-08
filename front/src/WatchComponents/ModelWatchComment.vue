<template>
    <div style="width: 50%; margin: 0 auto;">

        <!-- 评论 -->
        <div style="display: flex; padding-left: 20px; width: 100%;  margin-top: 20px; ">
            <div  class="tempAvater" >
                <img :src="avater" width="60px" height="60px">
            </div>
            <div style="width: 90%;">
                <h5>{{name}}</h5>
                <p class="contentP">{{content}}</p>
                <div class="lastLineTip">
                    <span class="timeSpan">&nbsp;&nbsp;{{time}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <el-button class="likeButton">赞</el-button>
                    <span class="tipSpan" v-if="likeNum != 0">{{likeNum}}</span> <span class="tipSpan" v-if="likeNum == 0"></span> &nbsp;&nbsp;

                    <el-button class="disLikeButton">踩</el-button>
                    <span class="tipSpan" v-if="disLikeNum != 0">{{disLikeNum}}</span> <span class="tipSpan" v-if="disLikeNum == 0"></span> &nbsp;&nbsp;

                    <el-button class="replyButton" @click="addReply(name, id, id)">回复</el-button>
                    
                </div>
                
            </div>
        </div>
        <!-- 回复 -->  
        <div style="display: flex; padding-left: 20px; width: 83%; margin: 0 auto;" v-for="reply in replys" :key="reply.repId">
            <div style="margin-top:20px" class="replyAvater">
                <img :src="avater" width="40px" height="40px">
            </div>
            <div style="margin-top: 20px">
                <div style="display: flex; width: 100%; ">
                    <h5>{{reply.user.uUsername}}</h5><h5 style="color: #3299cc" v-if="reply.repUsername != ''">&nbsp;回复@</h5><h5 style="color: #3299cc">{{reply.repUsername}}</h5>
                </div>
                
                <p class="contentP">{{reply.repContent}}</p>

                <div class="lastLineTip">
                    <span class="timeSpan">&nbsp;&nbsp;{{reply.repTime}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <el-button class="likeButton">赞</el-button>
                    <span class="tipSpan" v-if="reply.repLikeNum != 0">{{reply.repLikeNum}}</span> <span class="tipSpan" v-if="reply.repLikeNum == 0"></span> &nbsp;&nbsp;

                    <el-button class="disLikeButton">踩</el-button>
                    <span class="tipSpan" v-if="reply.repDisLikeNum != 0">{{reply.repDisLikeNum}}</span> <span class="tipSpan" v-if="reply.repDisLikeNum == 0"></span> &nbsp;&nbsp;

                    <el-button class="replyButton" @click="addReply(reply.user.uUsername, reply.repId, id)">回复</el-button>
                    
                </div>
            </div>
        </div>


        <div v-if="id == repCom && replyInputSeen == true">
            <!-- 回复 panel -->
            <div style="display: flex; padding-left: 20px;  padding-top: 20px; width: 100%;" >
                <!-- 头像 -->
                <div class="tempAvater" >
                    <img :src="avater" width="60px" height="60px">
                </div>

                <!-- 回复输入框与按钮 panel -->
                <div style="width:80%">
                    <!-- 回复输入框 -->
                    <el-input
                            style="margin-left: 10px; width: 100%; border: 1px;"
                            type="textarea"
                            :rows="3"
                            :placeholder="message"
                            v-model="newReply"
                    >
                    </el-input>
                    <!-- 发表评论与取消的按钮 -->
                    <div id="button_panel" style="margin-top: 10px; margin-bottom: 10px">
                        <el-button style="margin-left: 10px; padding: 4px" type="primary"  @click="showEmojiList">添加表情</el-button>
                        <el-button style="margin-left: 10px; padding: 4px" type="primary"  @click="removeReply">取消评论</el-button>
                    </div>

                </div>
                <!-- 发表评论的按钮 -->
                <div style="margin-left: 20px; width: 10%;">
                    <el-button style="padding: 4px; height: 75px; width: 100%; font-size: 13pt; line-height:24px;" type="primary"  @click="sendReply">发表<br>评论</el-button>
                </div>
            </div>
            <!-- 表情包显示区 -->
            <div style="margin-left: 80px; padding-left: 20px;">
                <div style="" v-if="emojiListSeen == 1">
                    <emoji-list v-on:emotion="addEmotionOnReply"></emoji-list>
                </div>
            </div>
            
        </div>



    </div>
</template>

<script>

// emoji components
import emojiList from '../pages/display/components/EmotionList.vue'

export default {

    data() {
        return {
            // replyInputSeen 判断回复框是否需要弹出的第二个条件（用户点击取消时使用）
            replyInputSeen: false,
            // emojiListSeen 
            emojiListSeen: -1,

            message: "回复 @ ", 
            tarId: -1,
            newReply: "",
        }
    },

    created:function() {
        
    },

    methods: {
        // 回复按钮响应
        // tarId 目标对象 id
        // parId 回复所属的评论的 id
        addReply(name, tarId, parId){
            this.replyInputSeen = true;
            this.emojiListSeen = -1;
            this.tarId = tarId;
            // alert(this.tarId);
            this.message = "回复 @ " + name;
            // 将回复按钮所属评论的 id 返回给父组件
            this.$emit("replyButtonListener", parId);
        },

        removeReply() {
            this.replyInputSeen = false;
        },

        sendReply() {

        },

        showEmojiList() {
            this.emojiListSeen = -this.emojiListSeen
        },

        /**
         * 回复内容添加表情包回显
         */
        addEmotionOnReply(data) {
            this.newReply = this.newReply + data;
        }
    },

    inheritAttrs: false,
    // props: ['avater','name','content', 'id', 'time', 'replys', 'replyCom'] 
    props: ['id', 'content', 'time', 'likeNum', 'disLikeNum', 'name', 'avater', 'replys', 'repCom'],


    components: { emojiList },

}
</script>

<style>

h5 {
    margin: 2px 0px 10px 10px;
}

.contentP {
    margin: 0px 0px 5px 10px;
}

.timeSpan {
    color: #b9b9b9;
    
}

.tipSpan {
    color: #b9b9b9;
    display: inline-block;
    width: 30px;
}

.lastLineTip {
    font-size: 2pt
}

.replyButton {
    width: 40px; 
    height: 20px;
    padding: 0px;
    border: 0px;
}

.likeButton {
    width: 20px; 
    height: 20px;
    padding: 0px;
    border: 0px;
}

.disLikeButton {
    width: 20px; 
    height: 20px;
    padding: 0px;
    border: 0px;
}

</style>
