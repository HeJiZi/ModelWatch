<template>
    <article v-loading="isLoading" style="display:flex;flex-flow:wrap row;">
        <div class="table-wrapper" >
            <div class="title">
                项目热度
            </div>
            <div style="background-color:white;">
              <ve-line :data="pjHeatStatic" :extend="lineExtend" :settings="chartSetting"></ve-line>
            </div>
        </div>
        <div class="table-wrapper" style="width:49%">
            <div class="title">
                模型统计
            </div>
            <div style="background-color:white;">
              <ve-histogram :data="modelStatic" :extend="chartExtend" :settings="chartSetting"></ve-histogram>
            </div>
        </div>
        
        <div class="table-wrapper" style="width:49%;margin-right:0px;margin-left:0px;">
            <div class="title">
                Top1: {{hotestModel.mName}}
            </div>
            <div style="overflow:hidden">
              <img :src="hotestModel.mPreview" width="600px" height="400px">
            </div>
        </div>        

        <div class="table-wrapper" style="width:49%">
            <div class="title">
                提交统计
            </div>
            <div style="background-color:white;">
              <ve-histogram :data="logStatic" :extend="chartExtend" :settings="chartSetting"></ve-histogram>
            </div>
        </div>
        <div class="table-wrapper" style="width:49%;margin-right:0px;margin-left:0px;">
            <div class="title">
                提交分布
            </div>
            <div style="background-color:white;">
                <ve-pie :data="users"></ve-pie>
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
        isLoading:true,
        chartSetting:{
            labelMap:{
                markNum:'收藏数',
                subNum:'关注数',
                comNum:'评论数',
                commitNum:'提交数'
            },
        },
        lineExtend:{
            series:{
                smooth:false
            }
        },
        chartExtend:{
            series (v) {
                v.forEach(i => {
                    i.barMaxWidth =30;
                })
                return v
            }
        },
        model:{
            mName:'logo',
            mPreview:'',
        },
        pjHeatStatic:{

            columns: ['pDay', 'markNum', 'subNum'],
            rows:[]
        },
        modelStatic:{
            columns: ['mName', 'comNum', 'markNum'],
            rows:[]
        },
        logStatic:{
            columns: ['lDay', 'commitNum'],
            rows:[]
        },
        hotestModel:{
        },
        users:{
            columns: ['uUsername', 'submitNum'],
            rows:[]
        }                        
      }
    },
    created(){
        this.isLoading =true;
        this.$http.get('/api/project/static/'+this.$route.params.pId).then((response)=>{
            var d = response.data;
            this.pjHeatStatic.rows = d.pjHeatStatic;
            if(d.modelStatic!=null){
                this.modelStatic.rows = d.modelStatic;
                this.hotestModel = d.hotestModel;
            }
            this.logStatic.rows = d.logStatic;
            this.users.rows = d.users;

            this.isLoading =false;
        })
    }
  }
</script>