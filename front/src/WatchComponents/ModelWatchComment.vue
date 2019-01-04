<template>
    <!-- 评论 -->
    <div style="display: flex;  width: 100%;  margin-top: 5px; ">
        <div  class="tempAvater" style="margin-right: 10px">
            <img :src="avater" width="70px" height="70px">
        </div>
        <div style="width: 90%;">
            <!-- 评论内容 -->
            <div>
                <h5>{{name}}</h5>
                <div class="contentP" v-html="content"></div>
                <div class="lastLineTip">
                    <span class="timeSpan">&nbsp;&nbsp;{{time}}</span>&nbsp;&nbsp;&nbsp;&nbsp;

                    <el-button class="likeButton" @click="modifyIdeaForComment(id, 1)">
                        <img src="/resources/images/pos_icon.png" width="20px" height="20px">
                    </el-button>
                    <span class="tipSpanPos" v-if="likeNum != 0">{{likeNum}}</span> 
                    <span class="tipSpanPos" v-if="likeNum == 0"></span> &nbsp;

                    <el-button class="disLikeButton" @click="modifyIdeaForComment(id, -1)">
                        <img src="/resources/images/obj_icon.png" width="20px" height="20px">
                    </el-button> 
                    <span class="tipSpanObj" v-if="disLikeNum != 0">{{disLikeNum}}</span> 
                    <span class="tipSpanObj" v-if="disLikeNum == 0"></span> &nbsp;

                    <el-button class="replyButton" @click="addReply(name, id, id, 0)">
                        <span v-if="needRepId == id && needRepType == 0 && needRepParId == repCom">取消</span>回复
                    </el-button>
                </div>
            </div>
            <!-- 评论输入面板 -->
            <div style="width: 100%">
                <e-input-panel 
                    :user="user", :repId="id",
                    :needRepId="needRepId", :keyType="0", 
                    :needRepType="needRepType", :comId="id", 
                    :repCom="repCom", :inputTip="inputTip",
                    v-on:closeCancelTip="closeCancel"
                    >
                </e-input-panel>
            </div>
            <!-- 循环显示回复及回复面板 -->  
            <div v-if="index > startShowReplyIndex && index < endShowReplyIndex" v-for="(reply, index) in replys" 
                :key="reply.repId">
                <!-- 回复内容 -->
                <div style=" width: 100%; display: flex; ">
                    <!-- 头像 -->
                    <div style="margin-top: 20px; margin-right: 10px" class="replyAvater">
                        <img :src="reply.user.uAvater" width="40px" height="40px">
                    </div>
                    <div style="margin-top: 20px">
                        <div style="display: flex; width: 100%; ">
                            <h5>{{reply.user.uUsername}}</h5>
                            <!--<h5 style="color: #409EFF; " v-if="reply.repRId != reply.repId">&nbsp;回复@</h5>-->
                            <h5 style="color: #409EFF; " v-if="reply.repRId != 0">&nbsp;回复@</h5>
                            <h5 style="color: #409EFF">{{reply.repUsername}}</h5>
                        </div>
                        
                        <div class="contentP" v-html="reply.repContent"></div>
                        <div class="lastLineTip">
                            <span class="timeSpan">&nbsp;&nbsp;{{reply.repCreateTime}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                            <el-button class="likeButton" @click="modifyIdeaForReply(index, 1)">
                                <img src="/resources/images/pos_icon.png" width="20px" height="20px">
                            </el-button>
                            <span class="tipSpanPos" v-if="reply.repLikeNum != 0">{{reply.repLikeNum}}</span>
                            <span class="tipSpanPos" v-if="reply.repLikeNum == 0"></span> &nbsp;

                            <el-button class="disLikeButton" @click="modifyIdeaForReply(index, -1)">
                                <img src="/resources/images/obj_icon.png" width="20px" height="20px">
                            </el-button> 
                            <span class="tipSpanObj" v-if="reply.repDisLikeNum != 0">{{reply.repDisLikeNum}}</span>
                            <span class="tipSpanObj" v-if="reply.repDisLikeNum == 0"></span> &nbsp;

                            <el-button class="replyButton" @click="addReply(reply.user.uUsername, reply.repId, id, 1)">
                                <span v-if="needRepId == reply.repId && needRepType == 1 && needRepParId == repCom">取消</span>回复
                            </el-button>
                        </div>
                    </div>
                </div>
                <!-- 针对回复的输入面板 -->
                <div style=" width: 100%">
                    <e-input-panel 
                        :user="user", :repId="reply.repId", 
                        :needRepId="needRepId", :keyType="1", 
                        :needRepType="needRepType", :comId="id", 
                        :repCom="repCom", :inputTip="inputTip",
                        v-on:closeCancelTip="closeCancel"
                        >
                    </e-input-panel>
                </div>
            </div>
            <!-- 回复分页 -->
            <div style=" margin-left: 40px; margin-top: 20px;">
                <el-pagination 
                    v-if="replys.length > this.EXTENDSHOWREPLYNUM && controlTipButtonSeen == -1"
                    :page-size="this.EXTENDSHOWREPLYNUM"
                    layout="prev, pager, next"
                    :total="replys.length"
                    :current-page.sync="page"
                    @next-click="nextPage"
                    @prev-click="prevPage"
                    @current-change="changePage"
                    >
                </el-pagination>
            </div>
            <!-- 点击按钮显示更多评论 -->
            <div style=" margin-top: 10px; margin-left: 21px;" v-if="replys.length > this.BASESHOWREPLYNUM">
                <el-button plain icon="el-icon-arrow-down" style=" width: 200px; border: 0px; color:#409EFF; font-weight: 700"
                    v-if="replys.length > this.BASESHOWREPLYNUM && controlTipButtonSeen == 1"
                    @click="showReplyControl(1)">
                    <span style="color:#409EFF;">共</span>
                    <span style="color: #ff85ad">{{replys.length}}</span>
                    <span style="color:#409EFF;">条回复</span>
                </el-button>
                <el-button plain icon="el-icon-arrow-up" style=" width: 200px; border: 0px; color:#409EFF; font-weight: 700" 
                    v-if="controlTipButtonSeen == -1"
                    @click="showReplyControl(-1)">
                    <span style="color:#409EFF;">点击收回所有回复</span>
                </el-button>
            </div>
            <hr style="color: #e5e9ef; border-style: solid; ">
        </div>
    </div>
</template>

<script>

import eInputPanel from '../pages/model/components/InputPanel.vue'

export default {
    props: ['user', 'id', 'content', 'time', 'likeNum', 'disLikeNum', 'name', 'avater', 'replys', 'repCom'],

    data() {
        return {
            // tarId: -1,
            // newReply: "",
            // @needRepParId 需要显示回复编辑框的评论 id
            needRepParId: -1,
            // @needRepId 需要显示回复编辑框的回复 id
            needRepId: -1,
            // @needRepType 用于确定需要显示回复编辑框的是评论（0）还是回复（1）
            needRepType: -1,
            // @inputTip 输入框顶部的提示信息
            inputTip: "回复@ ",
            // @BASESHOWREPLYNUM 最初每条评论下需要展示的回复数量
            BASESHOWREPLYNUM: 2,
            // @EXTENDSHOWREPLYNUM 点击展开回复按钮之后每条评论下需要显示的回复数量（每页的回复数量）
            EXTENDSHOWREPLYNUM: 5,
            // @page 分页时确定页数
            page: 1,
            // @startShowReplyIndex 需要显示的回复的开始下标
            startShowReplyIndex: -1,
            // @endShowReplyIndex 需要显示的回复的结束下标
            endShowReplyIndex: 2,
            // @controlTipButtonSeen 用于控制显示 '点击显示更多回复' 还是 '点击收回所有回复' 按钮
            controlTipButtonSeen: 1,
        }
    },

    created:function() {
        var replys = this.replys;
        var i, j;
        for (i = 0; i < replys.length; i++) {
            if (replys[i].repRId != replys[i].repId) {
                for (j = 0; j < replys.length; j++) {
                    if (replys[i].repRId == replys[j].repId) {
                        this.replys[i].repUsername = this.replys[j].user.uUsername;
                    }
                }
            }
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
                this.endShowReplyIndex = this.EXTENDSHOWREPLYNUM;
            } else {
                this.endShowReplyIndex = this.BASESHOWREPLYNUM;
                this.startShowReplyIndex = -1;
                // 收回回复的时候顺手把评论框关掉
                this.needRepId = -1;
                this.needRepType = -1;
                this.needRepParId = -1;
                if (signal != 0) {
                    // 回复页置一
                    this.page = 1;
                }
            }
            this.controlTipButtonSeen = - this.controlTipButtonSeen;
        },

        /**
         * 评论 点赞 点踩
         * @param id  回复/评论的 id
         * @param ideaType  点赞（1）/点踩（-1）
         */
        modifyIdeaForComment(id, ideaType) {
            // alert(this.$route.params.mId);

            this.$http.post('api/model/comidea/' + id + '/' + ideaType).then(
                function (response) {
                    if (response.data) {
                      if (ideaType == 1) {
                          this.likeNum = this.likeNum + 1;
                      } else {
                          this.disLikeNum = this.disLikeNum + 1;
                      }
                    } else {
                        alert("评论点赞/点踩出错");
                    }
                }
            );

        },

        /**
         *  回复 点赞 点踩
         * @param index
         * @param ideaType
         */
        modifyIdeaForReply(index, ideaType) {
            // alert(index);
            this.$http.post('api/model/repidea/' + this.replys[index].repId + '/' + ideaType).then(
                function (response) {
                      if (response.data) {
                          if (ideaType == 1) {
                              this.replys[index].repLikeNum = this.replys[index].repLikeNum + 1;
                          } else {
                              this.replys[index].repDisLikeNum = this.replys[index].repDisLikeNum + 1;
                          }
                      } else {
                          alert("回复点赞/点踩出错");
                      }
                }
            );
        },

        closeCancel(messId, messType) {
            this.needRepId = -1;
            this.needRepType = -1;
            this.needRepParId = -1;
            this.$http.get('/api/reply/show/new/' + this.id).then(
              function (response) {
                console.log(response.data);
                // this.comments = response.data;
                this.replys = response.data;

                this.page = parseInt((this.replys.length - 1) / this.EXTENDSHOWREPLYNUM + 1);
                this.showReplyControl(0);
                this.changePage();
                if (this.replys.length > 2) {
                  this.controlTipButtonSeen = -1;
                }
                var replys = this.replys;
                var i, j;
                for (i = 0; i < replys.length; i++) {
                    if (replys[i].repRId != replys[i].repId) {
                        for (j = 0; j < replys.length; j++) {
                            if (replys[i].repRId == replys[j].repId) {
                                this.replys[i].repUsername = this.replys[j].user.uUsername;
                            }
                        }
                    }
                }
                this.$emit("reflashReplys", this.replys, this.id);

              }
            );

        },

        nextPage() {
            this.startShowReplyIndex = this.startShowReplyIndex + this.EXTENDSHOWREPLYNUM;
            this.endShowReplyIndex = this.endShowReplyIndex + this.EXTENDSHOWREPLYNUM;
        },

        prevPage() {
            this.startShowReplyIndex = this.startShowReplyIndex - this.EXTENDSHOWREPLYNUM;
            this.endShowReplyIndex = this.endShowReplyIndex - this.EXTENDSHOWREPLYNUM;
        },

        changePage() {
            this.startShowReplyIndex = (this.page - 1) * this.EXTENDSHOWREPLYNUM - 1;
            this.endShowReplyIndex = this.page * this.EXTENDSHOWREPLYNUM;
        }
    },

    inheritAttrs: false,
    components: { eInputPanel },

}
</script>

<style>

h5 {
    margin: 2px 0px 10px 10px;
    font-size: 14px;
    color: #6d757a;
}

hr {
    color:#e7e7e7;
    margin: 20px 0px 20px 0px
}

.contentP {
    margin: 0px 0px 10px 10px;
    font-size: 18px;
}

.timeSpan {
    font-size: 14px;
    color: rgb(153, 162, 170);
}

.tipSpanPos {
    color: rgb(153, 162, 170);
    display: inline-block;
    width: 30px;
    font-weight: 500;
    font-size: 14px;
}

.tipSpanObj {
    color: rgb(153, 162, 170);
    display: inline-block;
    width: 30px;
    font-weight: 500;
    font-size: 14px;
}

.lastLineTip {
    font-size: 2pt;
    display: flex;
    align-items: center;
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
