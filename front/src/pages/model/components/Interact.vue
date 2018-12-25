<template>
    <div style="background: #ffffff; padding-bottom: 20px;">
        <!-- 顶部评论输入框 -->
        <div style="width: 53%; margin: 0 auto;">
            <e-input-panel 
                :user="user" :repId="0"
                :needRepId="0" :keyType="0"
                :needRepType="0" :comId="0" 
                :repCom="0" :inputTip="inputTip"
                >
            </e-input-panel>
        </div>
        <!-- 评论数量 -->
        <div style="width: 53%; margin: 0 auto">
            <span style="color: #6d757a; font-size: 20px; font-weight: 700; margin-left: 0px">评论数量</span>
            <span style="color: #409EFF; font-size: 28px; font-weight: 900; margin-left: 5px">{{comments.length}}</span>
        </div>
        <!-- 评论&回复模板 -->
        <div style="width: 53%; margin: 0 auto">
            <hr style="color: #e5e9ef; border-style: solid; margin-top: 5px">
            <mw-comment v-for="comment in comments" v-on:replyButtonListener="setRepCom" :key="comment.comId"
                        :user="user"
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
        <!-- 分页 -->
        <div style="width: 53%; margin: 0 auto;">
            <el-pagination v-if="commentsInfo.totalNum >= commentsInfo.pageSize"    
                background
                :page-size="commentsInfo.pageSize"
                layout="prev, pager, next"
                :total="commentsInfo.totalNum"
                :current-page.sync="commentsInfo.curPage"
                @next-click="nextPage"
                @prev-click="prevPage"
                @current-change="changePage"
                >
            </el-pagination>
        </div>
    </div>
</template>


<script>
import mwComment from '@/WatchComponents/ModelWatchComment.vue'
import eInputPanel from './InputPanel.vue'

export default {
    data() {
        return{
            // @replyCom 用于提示子组件当前需要显示回复框的评论编号
            repCom: -1,
            // @inputTip 输入框顶部的提示信息
            inputTip: "请自觉遵守互联网相关的法律法规，文明发表评论！",
            // @newComments 新评论内容（可能要被废除掉）
            // newComments:'',
            // @user 当前用户的信息
            user:{
                uAvater:'/resources/images/small_logo.png',
                uId:'',
            },
            // @commentsInfo 评论信息（评论总数，当前页面） 用于分页
            commentsInfo: {
                totalNum: 200,
                curPage: 1,
                pageSize: 8,
            },
            // @comments 列表 评论回复，测试用
            comments: [{
                comId: 1, comContent: '这看起来真棒啊', comCreateTime: '2017-09-21 08:12:23', comLikeNum: 10, comDisLikeNum: 0, 
                user: {uUsername: '隔壁老王', uAvater: '/resources/images/small_logo.png'},
                replys: [{
                    repId: 1, repContent: '是啊是啊', repTime: '2017-09-21 09:12:23', repLikeNum: 2, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '小孙', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 2, repContent: '放屁', repTime: '2017-09-21 10:12:23', repLikeNum: 0, repDisLikeNum: 12, repUsername: '小孙',
                    user: {uUsername: '傻屌', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 25, repContent: '你才放屁', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '傻屌',
                    user: {uUsername: '舞法天女', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 26, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '26', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 27, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '27', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 28, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '28', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 29, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '29', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 30, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '30', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 31, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '31', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 32, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '32', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 33, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '33', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 34, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '34', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 35, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '35', uAvater: '/resources/images/small_logo.png'}
                }]
            }, {
                comId: 2, comContent: '编不下去了', comCreateTime:  '2016-09-21 08:12:23', comLikeNum: 12, comDisLikeNum: 0,
                user: {uUsername: '钢铁侠', uAvater: '/resources/images/small_logo.png'},
                replys: [ {
                    repId: 37, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '37', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 38, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '38', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 39, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '39', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 40, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '40', uAvater: '/resources/images/small_logo.png'}
                }]
            }, {
                comId: 3, comContent: '编不下去了', comCreateTime:  '2016-09-21 08:12:23', comLikeNum: 12, comDisLikeNum: 0,
                user: {uUsername: '钢铁侠', uAvater: '/resources/images/small_logo.png'},
                replys: []
            }, {
                comId: 4, comContent: '编不下去了', comCreateTime:  '2016-09-21 08:12:23', comLikeNum: 12, comDisLikeNum: 0,
                user: {uUsername: '钢铁侠', uAvater: '/resources/images/small_logo.png'},
                replys: [{
                    repId: 41, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '41', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 42, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '42', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 43, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '43', uAvater: '/resources/images/small_logo.png'}
                }, {
                    repId: 44, repContent: 'this is a test', repTime: '2017-09-21 10:18:23', repLikeNum: 10, repDisLikeNum: 0, repUsername: '',
                    user: {uUsername: '44', uAvater: '/resources/images/small_logo.png'}
                }]
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
        /**
         * 评论审查
         * 这个方法已经被转移到 InputPanel 组件
         */
        // review(){
        //     var url = window.location.href;
        //     var id = url.substr(url.lastIndexOf('/') + 1, url.length);
        //     this.$http.post('/model/comment',{
        //         comContent:this.newComments,
        //         comUId:this.user.uId,
        //         comMId:id,
        //     }).then((response) => {
        //         if(response.data == true) alert("评论成功，请等待管理员审核");
        //         else alert("评论失败，存在异常操纵")
        //     })
        // },

        /**
         * 设置当前点击了回复按钮的评论 id
         */
        setRepCom(data) {
            this.repCom = data;
        },

        nextPage() {
            // 测试用
            this.comments = [{
                comId: 7, comContent: '编去了', comCreateTime:  '2016-09-21 08:12:23', comLikeNum: 12, comDisLikeNum: 0,
                user: {uUsername: '钢侠', uAvater: '/resources/images/small_logo.png'},
                replys: []
            }]
        },

        prevPage() {

        },

        changePage() {
       
        }

    },

    components:{ mwComment, eInputPanel }, 

}

</script>

<style>
    .tempAvater{
        width: 70px;
        height: 70px;
        float: left;
        border-radius: 50%;
        border: 2px solid white;
        overflow: hidden;
        cursor: pointer;
        z-index: 99;
    }
    #comments>div{
        border-bottom: 1px solid #e1e1e1;
        padding-bottom: 20px;
    }
    #comments div{
        margin: 10px 20px;
    }
</style>
