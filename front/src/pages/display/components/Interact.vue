<template>
    <div style="background: #ffffff; padding-bottom: 20px;">
        <div style="width: 60%; margin: 0 auto;">
            <!-- 评论 panel -->
            <div style="display: flex;padding-top: 20px; padding-left: 20px;width: 100%; justify-content: center">
                <!-- 头像 -->
                <div  class="tempAvater" >
                    <img :src="user.uAvater" width="60px" height="60px">
                </div>

                <div style="width:80%">
                    <!-- 评论输入框 -->
                    <el-input
                            style="width: 100%; border: 1px; margin-left: 10px;"
                            type="textarea"
                            :rows="3"
                            :placeholder="message"
                            v-model="newComments"
                            >
                    <!-- v-model="newComments" 这个是用来做什么的？ -->
                    </el-input>
                    
                    <!-- 添加表情 -->
                    <div id="button_panel" style="margin-top: 10px; margin-bottom: 10px">
                        <el-button style=" margin-left: 10px; padding: 4px" type="primary"   @click="showEmojiList">添加表情</el-button>
                    </div>
                </div>
                <!-- 发表评论的按钮 -->
                <div style="margin-left: 20px; width: 10%;">
                    <el-button style="padding: 4px; height: 75px; width: 100%; font-size: 13pt;line-height:24px;" type="primary" @click="review">发表<br>评论</el-button>
                </div>
                
                
            </div>
            <!-- 表情包显示区 -->
            <div style="margin-left: 80px; padding-left: 20px;">    
                <div style="" v-if="emojiListSeen == 1">
                    <emoji-list v-on:emotion="addEmotionOnComment"></emoji-list>
                </div>
            </div>

        </div>
        <!-- 评论&回复模板 -->
        <mw-comment v-for="comment in comments" v-on:replyButtonListener="setRepCom" :key="comment.comId"
                    :id="comment.comId"
                    :content="comment.comContent"
                    :time="comment.comCreateTime"
                    :likeNum="comment.comLikeNum"
                    :disLikeNum="comment.comDisLikeNum"
                    :name="comment.user.uUsername"
                    :avater="comment.user.uAvater" 
                     
                    :replys="comment.replys"

                    :repCom="repCom" 
                    >
        </mw-comment>


    </div>
</template>


<script>
import mwComment from '@/WatchComponents/ModelWatchComment.vue'

// emoji components
import emojiList from './EmotionList.vue'

export default {
    data(){
        return{
            emojiListSeen: -1,

            // replyCom 用于提示子组件当前需要显示回复框的评论编号
            repCom: -1,

            // newCommentContent: "",
            message: "请输入内容",

            // 新评论 (数据还没有整理)
            newComments:'',
            user:{
                uAvater:'/static/images/small_logo.png',
                uId:'',
            },

            // 评论列表
            comments: [{
                comId: 1, comContent: '这看起来真棒啊', comCreateTime: '2017-09-21 08:12:23', comLikeNum: 10, comDisLikeNum: 0, 
                user: {uUsername: '隔壁老王', uAvater: '/static/images/small_logo.png'},
                replys: [{
                    repId: 21, repContent: '是啊是啊', repTime: '2017-09-21 09:12:23', repLikeNum: 2, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '小孙', uAvater: '/static/images/small_logo.png'}
                }, {
                    repId: 24, repContent: '放屁', repTime: '2017-09-21 10:12:23', repLikeNum: 0, repDisLikeNum: 12, repUsername: '小孙',
                    user: {uUsername: '傻屌', uAvater: '/static/images/small_logo.png'}
                }, {
                    repId: 25, repContent: '你才放屁', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '傻屌',
                    user: {uUsername: '舞法天女', uAvater: '/static/images/small_logo.png'}
                }]
            }, {
                comId: 2, comContent: '编不下去了', comCreateTime:  '2016-09-21 08:12:23', comLikeNum: 12, comDisLikeNum: 0,
                user: {uUsername: '钢铁侠', uAvater: '/static/images/small_logo.png'},
                replys: []
            }]


        }
    },
    created:function() {
        //Todo:ajax获取评论数据

        // var url = window.location.href;
        // var id = url.substr(url.lastIndexOf('/') + 1, url.length);
        // this.$http.get('/model/' + id + '/comments').then((response) => {
        //     this.comments = response.data
        // })
        // this.$http.get('/user/').then((response) => {
        //     this.user = response.data;
        // })
    },
    methods:{
        review(){
            var url = window.location.href;
            var id = url.substr(url.lastIndexOf('/') + 1, url.length);
            this.$http.post('/model/comment',{
                comContent:this.newComments,
                comUId:this.user.uId,
                comMId:id,
            }).then((response) => {
                if(response.data == true) alert("评论成功，请等待管理员审核");
                else alert("评论失败，存在异常操纵")
            })
        },

        /**
         * 设置当前点击了回复按钮的评论 id
         */
        setRepCom(data) {
            this.repCom = data;
        },

        /**
         * 控制是否显示表情包显示区
         */
        showEmojiList() {
            this.emojiListSeen = -this.emojiListSeen
        },

        /**
         * 评论内容添加表情包回显
         */
        addEmotionOnComment(data) {
            this.newComments = this.newComments + data;
        },

    },
    components:{ mwComment, emojiList }, 
}
</script>

<style>
    .tempAvater{
        width: 60px;
        height: 60px;
        float: left;
        border-radius: 50%;
        border: 2px solid white;
        overflow: hidden;
        cursor: pointer;
        z-index: 10000;
    }
    #comments>div{
        border-bottom: 1px solid #e1e1e1;
        padding-bottom: 20px;
    }
    #comments div{
        margin: 10px 20px;
    }
</style>
