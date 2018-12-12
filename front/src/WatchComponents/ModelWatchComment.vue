<template>
    <div style="width: 53%; margin: 0 auto;">

        <!-- 评论 -->
        <div style="display: flex;  width: 100%;  margin-top: 20px; ">
            <div  class="tempAvater" >
                <img :src="avater" width="60px" height="60px">
            </div>
            <div style="width: 90%;">
                <h5>{{name}}</h5>
                <p class="contentP">{{content}}</p>
                <div class="lastLineTip">
                    <span class="timeSpan">&nbsp;&nbsp;{{time}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <el-button class="likeButton">赞</el-button>
                    <span class="tipSpan" v-if="likeNum != 0">{{likeNum}}</span> 
                    <span class="tipSpan" v-if="likeNum == 0"></span> &nbsp;&nbsp;

                    <el-button class="disLikeButton">踩</el-button>
                    <span class="tipSpan" v-if="disLikeNum != 0">{{disLikeNum}}</span> 
                    <span class="tipSpan" v-if="disLikeNum == 0"></span> &nbsp;&nbsp;

                    <el-button class="replyButton" @click="addReply(name, id, id, 0)">
                        <span v-if="needRepId == id && needRepType == 0 && needRepParId == repCom">取消</span>回复
                    </el-button>
                    
                </div>
            </div>
        </div>

        <div style="margin: 0 auto; width: 85%">
            <e-input-panel 
                :avater="avater", :repId="id", 
                :needRepId="needRepId", :keyType="0", 
                :needRepType="needRepType", :comId="id", 
                :repCom="repCom", :message="inputTip",
                :newComments="newComments" 
                >
            </e-input-panel>
        </div>

        <!-- 循环显示回复及回复面板 -->  
        <div v-if="index < needShowReplyNum" v-for="(reply, index) in replys" 
            :key="reply.repId">

            <div style="display: flex; width: 85%; margin: 0 auto;">
                <div style="margin-top:20px" class="replyAvater">
                    <img :src="avater" width="40px" height="40px">
                </div>
                <div style="margin-top: 20px">
                    <div style="display: flex; width: 100%; ">
                        <h5>{{reply.user.uUsername}}</h5>
                        <h5 style="color: #409EFF" v-if="reply.repUsername != ''">&nbsp;回复@</h5>
                        <h5 style="color: #409EFF">{{reply.repUsername}}</h5>
                    </div>
                    
                    <p class="contentP">{{reply.repContent}}</p>

                    <div class="lastLineTip">
                        <span class="timeSpan">&nbsp;&nbsp;{{reply.repTime}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        <el-button class="likeButton">赞</el-button>
                        <span class="tipSpan" v-if="reply.repLikeNum != 0">{{reply.repLikeNum}}</span> 
                        <span class="tipSpan" v-if="reply.repLikeNum == 0"></span> &nbsp;&nbsp;

                        <el-button class="disLikeButton">踩</el-button>
                        <span class="tipSpan" v-if="reply.repDisLikeNum != 0">{{reply.repDisLikeNum}}</span>
                        <span class="tipSpan" v-if="reply.repDisLikeNum == 0"></span> &nbsp;&nbsp;

                        <el-button class="replyButton" @click="addReply(reply.user.uUsername, reply.repId, id, 1)">
                            <span v-if="needRepId == reply.repId && needRepType == 1 && needRepParId == repCom">取消</span>回复
                        </el-button>
                        
                    </div>
                </div>
            </div>

            <div style="margin: 0 auto; width: 85%">
                <e-input-panel 
                    :avater="avater", :repId="reply.repId", 
                    :needRepId="needRepId", :keyType="1", 
                    :needRepType="needRepType", :comId="id", 
                    :repCom="repCom", :message="inputTip",
                    :newComments="newComments"
                    >
                </e-input-panel>
            </div>

        </div>

        <br>

        <!-- 点击按钮显示更多评论 -->
        <div style="justify-content: center; display: flex;" >
            <el-button plain icon="el-icon-arrow-up" style=" width: 200px; border: 0px; color:#409EFF" 
                
                v-if="needShowReplyNum > 3" 
                @click="showReplyControl(-1)">
                <span style="color:#409EFF;">点击收回所有回复</span>
            </el-button>
            <el-button plain icon="el-icon-arrow-down" style=" width: 200px; border: 0px; color:#409EFF" 
                v-if=" replys.length - needShowReplyNum >= 1"
                @click="showReplyControl(1)">
                <span style="color:#409EFF;">点击显示更多回复</span>
            </el-button>
        </div>

    </div>
</template>

<script>

import eInputPanel from '../pages/display/components/InputPanel.vue'

export default {

    data() {
        return {
            tarId: -1,
            newReply: "",

            // 显示回复框用
            needRepParId: -1,
            needRepId: -1,
            needRepType: -1,

            // 输入框显示提示信息
            inputTip: "回复@ ",

            // 测试 控制需要显示的回复数量
            needShowReplyNum: 3,
        }
    },


    methods: {
        /**
         * 回复按钮响应
         * tarId 目标对象（回复或评论） id
         * parId 回复所属的评论的 id
         * type 用于确定是评论（0）是回复（1）
         */
        addReply(name, tarId, parId, type){
            // 将回复按钮所属评论的 id 返回给父组件
            this.$emit("replyButtonListener", parId);
            this.inputTip = "回复 @ " + name;
            if (this.needRepId == tarId && this.needRepType == type && this.needRepParId == parId) {
                this.needRepId = -1;
                this.needRepType = -1;
                this.needRepParId = -1;
            } else {
                this.needRepId = tarId;
                this.needRepType = type;
                this.needRepParId = parId;
            }
        },

        /**
         * 点击按钮之后显示或收回回复内容
         */
        showReplyControl(signal) {

            if (signal == 1) {
                this.needShowReplyNum = this.needShowReplyNum + 3;
            } else {
                this.needShowReplyNum = 3;
                // 收回回复的时候顺手把评论框关掉
                this.needRepId = -1;
                this.needRepType = -1;
                this.needRepParId = -1;
            }
            
        },

    },

    inheritAttrs: false,
    props: ['id', 'content', 'time', 'likeNum', 'disLikeNum', 'name', 'avater', 'replys', 'repCom', 'newComments'],
    components: { eInputPanel },

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
    width: 60px; 
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
