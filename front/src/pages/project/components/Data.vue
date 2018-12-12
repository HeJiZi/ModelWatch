<template>
    <article  style="display:flex;flex-flow:wrap row;">
        <div class="table-wrapper" >
            <div class="title">
                项目热度
            </div>
            <div style="background-color:white;">
              <ve-line :data="chartData"></ve-line>
            </div>
        </div>
        <div class="table-wrapper" style="width:49%">
            <div class="title">
                模型统计
            </div>
            <div style="background-color:white;">
              <ve-histogram :data="chartData"></ve-histogram>
            </div>
        </div>
        
        <div class="table-wrapper" style="width:49%;margin-right:0px;margin-left:0px;">
            <div class="title">
                Top1: {{model.mName}}
            </div>
            <div style="overflow:hidden">
              <img :src="model.mPreview" width="600px" height="400px">
            </div>
        </div>        

        <div class="table-wrapper" style="width:49%">
            <div class="title">
                提交统计
            </div>
            <div style="background-color:white;">
              <ve-histogram :data="chartData"></ve-histogram>
            </div>
        </div>
        <div class="table-wrapper" style="width:49%;margin-right:0px;margin-left:0px;">
            <div class="title">
                提交分布
            </div>
            <div style="background-color:white;">
                <ve-pie :data="chartData"></ve-pie>
            </div>
        </div>    
   

    </article>
</template>
<style>
  .table-wrapper{
    width: 100%;
    
    margin: 5px 5px 20px 5px;
    border: 1px solid rgb(204, 210, 230);
    border-radius: 5px;
    background: rgb(242, 246, 252);
  }

</style>

<script>
   export default {
    data () {
      return {
        chartData: {
          columns: ['日期', '收藏数', '关注数'],
          rows: [
            { '日期': '2018-04-22', '收藏数': 32371, '关注数': 19810 },
            { '日期': '2018-05-23', '收藏数': 12328, '关注数': 4398 },
            { '日期': '2018-05-25', '收藏数': 12328, '关注数': 4398 },
            { '日期': '2018-05-27', '收藏数': 12328, '关注数': 4398 },
            { '日期': '2018-05-28', '收藏数': 12328, '关注数': 4398 },
            { '日期': '2018-05-24', '收藏数': 92381, '关注数': 52910 }
          ]
        },
        model:{
            mName:'logo',
            mPreview:'/static/images/1.png',
        }
      }
    }
  }
</script>