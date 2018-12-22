<template>
    <el-card class="box-card">
        <div slot="header" >
            <span><i class="el-icon-message"></i><span style="color:rgb(137, 150, 173)"> {{userName}} 的邀请函</span></span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
        </div>
        <div class="inv">
            <el-row style="font-size:20px;margin-bottom:30px;padding-top:20px;font-family:'Times New Roman', Times, serif;">邀请信息</el-row>
            <el-row>
                <img src='/static/images/temp.jpeg' class="figure" >
                <i class="el-icon-plus" style="margin:0px 30px"></i>
                <img src='/static/images/temp.jpeg' class="figure" >
            </el-row>    
            <el-row>
                <div style="padding-top:20px;padding-bottom:20px;">
                <span class="tag"><a href="">@{{userName}}</a>邀请你在{{pidName}}<br>项目上进行协作</span>
                </div>
            </el-row>
            <el-row>
                <div style="padding-top:20px;padding-bottom:20px;border-bottom:1px solid rgb(233, 237, 245); ">
                    <el-button type="primary" plain>接受邀请</el-button>
                    <el-button type="info" plain>拒绝邀请</el-button>
                </div>
            </el-row>
            <el-row>
                <div style="padding-top:20px;padding-bottom:25px;font-size:18px;text-align:left;padding-left:190px;">
                    您可以接受或拒绝这个邀请。您也可以去查阅<a href="">{{pidName}}</a><br>资料库或访问以了解更多...
                </div>
            </el-row>
        </div>
    </el-card>

</template>

<style>
  .box-card {
    width: 800px;
  }
  .inv{
      position: relative;
      align-content:center;
      text-align: center;
      background-color:rgb(242, 246, 252);
  }
  .figure{
      border-color:rgb(175, 198, 241);
      border-width: 3px;
      border-style: solid;
      border-radius: 5px;
      width: 80px;
      width: 80px;
  }
    /* .content{
        border:1px rgb(193, 218, 247) solid;
        margin:20px 100px 20px 100px;
        padding-top:20px;
        height: 300px;
        -moz-window-shadow: ;
    } */
    .el-card__header{
        background-color:rgb(233, 237, 245); 
    }
    .el-card__body{
        padding:0px;
    }
    .tag{
        font-family: 'Times New Roman', Times, serif;
        font-size: 25px;
    }
    img:hover{ transform:scale(1.02,1.02)}
    a:link{
        text-decoration:none;
    }
    a:visited{
        text-decoration:none;
    }
    a:hover{text-decoration:underline;}
    a:active{text-decoration:none;}
</style>

<script>
export default {
    data(){
        return{
            userName:'HeJiZi',
            pidName:'modalWatch'
        }
    }
}
</script>
