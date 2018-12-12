<template>
  <div class="tw-wrapper">
    <el-row style="font-size: 15px;padding: 10px 10px;font-weight: bold;height: 40px;color: #606d79;">
      协作者 <strong id="addIcon" @click="inputBoxHeight=inputBoxHeight=='0px'?'80px':'0px'" >{{inputBoxHeight=='0px'?'+':'-'}}</strong>
    </el-row>
    <el-row id="userSearchBox" style="background-color: white;border:1px solid rgb(232, 236, 242);" :style="{height:inputBoxHeight}" >
      <div style="width:800px;margin:20px auto;" >
          <el-autocomplete style="width:600px;"
            v-model="name"
            :fetch-suggestions="querySearch"
            placeholder="请输入关键词"
            :trigger-on-focus="false">
            <template slot-scope="{item}">
              <div style="margin:10px 0px;">
                <div class="avatar" style="width:30px;height:30px">
                  <img :src='item.uAvater' width="30px" height="30px" >
                </div>
                <span style="color: #564545;margin-left: 5px;font-size: 10px;">{{item.uUsername}}</span>
              </div>
            </template> 
          </el-autocomplete>
          <el-button type="primary" @click="addCollaborators">添加邀请</el-button>
      </div>
    </el-row>
    <el-table
      :data="collaborators"
      :show-header="false"
      style="width:100%;text-align:center;">

        <el-table-column >
            <template slot-scope="scope">
              <article>
                <el-popover trigger="hover" placement="left-start">
                <span style="display: inline-block;vertical-align: middle;">
                  <b>邮箱：{{ scope.row.uEmail }}</b><br>
                  <br>
                  <b>生日：{{ scope.row.uBirthday }}</b><br>
                </span>
                <div slot="reference" class="name-wrapper" >
                  <div class="avatar">
                    <img :src='scope.row.uAvater' width="60px" height="60px" >
                  </div>
                </div>
              </el-popover>
              <div style="margin:0px 0px 0px 70px;cursor: pointer;" >
                <strong>{{scope.row.uUsername}}</strong>
                <p style="margin:10px 0px 0px 0px">{{scope.row.uSignature}}</p>
              </div>
              </article>

            </template>
        </el-table-column>
    
        <el-table-column >
          <template slot-scope="scope">
            <div style="display:flex;justify-content: flex-end;margin-right:20px;">
              <template v-if="scope.row.invState == 1">
                <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
              <template v-else>
                <span style="padding-top:2px;margin-right:20px;">正在等待该用户的回应...</span>
                <el-button size="mini">复制链接</el-button>
                <el-button size="mini" type="danger">取消邀请</el-button>
              </template>

            </div>

          </template>
        </el-table-column>
        <el-table-column width="200px">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.invTime }}</span>
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
  .avatar{
    
      width: 60px;
      height: 60px;
      float: left;
      border-radius: 5px;
      border: 2px solid white;
      overflow: hidden;
      cursor: pointer;
      z-index: 10000;
  }

  #addIcon{
    position: relative;
    top: -2px;
    cursor: pointer;
  }
  #addIcon:hover{
    color: black;
  }

  #userSearchBox{
    transition: height ease 0.4s;
  }

</style>
<script>
  export default {
    data() {
      return {
        users: [],
        name: '',
        inputBoxHeight:'0px',
        page:1,
        focusState:false,
        collaborators: [{
          uId:1,
          uUsername: 'HeJiZi',
          uSignature:'太阳照常升起',
          invTime:'2018-12-07 12:00:00',
          uAvater:'/static/images/temp.jpeg',
          uEmail:"129312398@qq.com",
          uBirthday:'2018-07-05',
          invState: '0'
        }, {
          uId:2,
          uUsername: 'LiAn',
          uSignature:'这是一段个性签名',
          invTime:'2018-12-07 14:00:00',
          uAvater:'/static/images/small_logo.png',
          uBirthday:'2018-07-05',
          uEmail:"129312398@qq.com",
          invState: '1'
        }]
      };
    },
    methods:{
      querySearch(queryString, cb) {
        var users = this.users;
        var results = queryString ? users.filter(this.createFilter(queryString)) : users;
        cb(results);
      },
      createFilter(queryString) {
        return (users) => {
          return (users.uUsername.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      loadAll() {
        return [
          { uAvater: "/static/images/small_logo.png", uUsername: "LiAn" },
          { uAvater: "/static/images/temp.jpeg", uUsername: "HeJiZi" },
          { uAvater: "/static/images/temp.jpeg", uUsername: "Haha" },
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
      this.users = this.loadAll();
      console.log(this.users);
    }
  }
</script>
