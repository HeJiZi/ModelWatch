<template>
    <div style="background: #ffffff;">
        <div style="display: flex;padding-top: 20px;padding-left: 20px;width: 80%;margin: 0 auto;align-items: center;justify-content: center">
            <div  class="tempAvater" >
                <img :src="user.uAvater" width="60px" height="60px">
            </div>
            <el-input
                    style="width: 50%;"
                    type="textarea"
                    :rows="3"
                    placeholder="请输入内容"
                    v-model="newComments">
            </el-input>
            <el-button style="height: 80px;" type="primary" plain @click="review">发表评论</el-button>
        </div>
        <mw-comment v-for="comment in comments" :name="comment.user.uUsername"
                    :avater="comment.user.uAvater" :time="comment.comCreateTime"
                    :content="comment.comContent"  :key="comment.id">
        </mw-comment>
    </div>
</template>


<script>
import mwComment from '@/WatchComponents/ModelWatchComment.vue'
export default {
    data(){
        return{
            newComments:'',
            user:{
                uAvater:'/static/images/small_logo.png',
                uId:'',
            },
            comments:[{
                id:1,
                user:{
                    uUsername:'王虎',
                    uAvater:'/static/images/small_logo.png'
                },
                comUid:'1',
                comContent:'这模型做得真精致',
                comCreateTime:'2011-1-1',
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
        }
    },
    components:{ mwComment }, 
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
