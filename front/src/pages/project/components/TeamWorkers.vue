<template>
  <div class="tw-wrapper">
    <el-row style="font-size: 15px;padding: 5px 10px;font-weight: bold;background: rgb(242, 246, 252);height: 30px;color: #606d79;">
      协作者
    </el-row>
    <el-row style="margin:0px;" >
        <el-autocomplete style="width:600px;"
          v-model="state"
          :fetch-suggestions="querySearch"
          placeholder="请输入关键词"
          :trigger-on-focus="false">    
        </el-autocomplete>
        <el-button type="primary" @click="addCollaborators">添加邀请</el-button>
    </el-row>
    <el-table
      :data="tableData"
      :show-header="false"
      style="width:100%;text-align:center;">

        <el-table-column width="500">
            <template slot-scope="scope">
              <!-- <el-popover trigger="hover" placement="top">
                <el-upload
                  class="avatar-uploader"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload" style="display:inline-block;vertical-align: middle;" >
                    <img :src="scope.row.imageUrl" class="avatar" style="width:90px;height:90px;line-height:90px">
                </el-upload>
                <span style="display: inline-block;vertical-align: middle;">
                  <b>{{ scope.row.name }}</b><br>
                  <span>{{ scope.row.address }}</span>
                </span>
                <div slot="reference" class="name-wrapper" style="margin-left:100px">
                  <el-upload
                    class="avatar-uploader"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload" style="display:inline-block;vertical-align:top" >
                      <img :src='scope.row.imageUrl' class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                  <el-tag size="medium" style="margin-top:10px" v-if="awaitingValidate()">{{scope.row.name}}</el-tag>
                  <span v-else style="margin-left:20px;">{{validation}}</span>
                </div>
              </el-popover> -->
            </template>
        </el-table-column>
        <el-table-column width="180" >
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.date }}</span>
            </template>
        </el-table-column>
        <el-table-column >
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
    </el-table>

    <el-pagination
      :page-size="5"
      :pager-count="11"
      layout="prev, pager, next"
      :total="50"
      style="margin:0px 250px"
      :current-page.sync="page"
      @next-click="nextPage"
      @current-change="changePage">
    </el-pagination>     
  </div>
</template>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #cfd5dd;
    width: 70px;
    height: 70px;
    line-height: 70px;
    text-align: center;
  }
  .avatar {
    width: 70px;
    height: 70px;
    display: block;
  }
  .tw-wrapper{
    margin: 5px;
    border: 1px solid rgb(204, 210, 230);
    border-radius: 5px;
  }

</style>
<script>
  export default {
    data() {
      return {
        restaurants: [],
        state: '',
        page:1,
        validation:'等待验证...',
        focusState:false,
        tableData: [{
          date:'2018-12-07',
          imageUrl:'/static/images/1.jpg',
          name: '王虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date:'2018-12-01',
          imageUrl:'/static/images/1.jpg',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }]
      };
    },
    methods:{
      querySearch(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      loadAll() {
        return [
          { "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
          { "value": "Hot honey 首尔炸鸡（仙霞路）", "address": "上海市长宁区淞虹路661号" },
          { "value": "新旺角茶餐厅", "address": "上海市普陀区真北路988号创邑金沙谷6号楼113" },
          { "value": "泷千家(天山西路店)", "address": "天山西路438号" }
        ];
      },

      handleSelect(item) {
        console.log(item);
      },
      nextPage(){
        console.log("nextPage");
        this.tableData=[{
          date: '2017-02-01',
          name: '王虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }]
      },
      changePage(){
        console.log("changePage"+this.page);
      },

      // handleAvatarSuccess(res, file) {
      //     this.imageUrl = URL.createObjectURL(file.raw);
      // },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      //等待验证
      awaitingValidate(){
        var result=false;
        return result;
      },
      // 按钮添加协作者
      addCollaborators(){
        if(this.state){
          this.tableData.push({date:'2018-12-11',
                  imageUrl:'/static/images/temp.jpeg',
                  name:this.state,
                  address: '上海市普陀区金沙江路 1519 弄'});
          this.state='';
        }
        else{
          this.focusState=true;
        }
      },
      handleDelete(index,row){
        this.tableData.splice(index,1);
      }
    },
    mounted() {
      this.restaurants = this.loadAll();
      console.log(this.restaurants);
    }
  }
</script>
