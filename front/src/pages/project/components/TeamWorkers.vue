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
                <el-popover trigger="hover" placement="right-start">
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
                <el-popover
                  placement="bottom"
                  width="250"
                  trigger="click"
                  offset=0>
                  <div style="margin-bottom:10px">邀请链接:</div>
                  <div class="me" style="border:2px solid rgb(57, 139, 211);display:flex;border-radius:3px;">
                    <el-input :value=scope.row.uEmail readonly id="nu"  
                      style="width:200px;display:inline-block;vertical-align:middle;"></el-input>
                    <el-button icon="el-icon-edit-outline" style="border: 0px;margin-left: -10px;height: 40px;width:55px;" @click="copy"></el-button>                
                  </div>
                  <el-button size="mini" slot="reference">复制链接</el-button>
                </el-popover>
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
  .tw-wrapper{
    margin: 5px;
    border: 1px solid rgb(204, 210, 230);
    border-radius: 5px;
    background: rgb(242, 246, 252);
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
  .el-input>input{   
    border-radius: 0;
  }
  .el-input>input:focus{
    border-color:rgb(140, 175, 228);
    outline:rgb(140, 175, 228) solid 2px;
  }
  .me>.el-input>input{
    border-right-color: rgb(140, 175, 228);
    border-left:0;
    border-bottom:0;   
    border-top: 0;
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
        collaborators: [
        {
          uId:1,
          uUsername: 'HeJiZi',
          uSignature:'太阳照常升起',
          invTime:'2018-12-07 12:00:00',
          uAvater:'/resources/images/temp.jpeg',
          uEmail:"129312398@qq.com",
          uBirthday:'2018-07-05',
          invState: '0'
        }, {
          uId:2,
          uUsername: 'LiAn',
          uSignature:'这是一段个性签名',
          invTime:'2018-12-07 14:00:00',
          uAvater:'/resources/images/small_logo.png',
          uBirthday:'2018-07-05',
          uEmail:"129312398@qq.com",
          invState: '1'
        }
        ]
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
      // handleSelect(item) {
      //   console.log(item);
      // },
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
      addCollaborators(){
        if(this.name){
          this.collaborators.push({
                  uId:'',
                  uUsername:this.name,
                  uSignature:'这是一段个性签名',
                  invTime:'2018-12-07 14:00:00',
                  uAvater:'/static/images/temp.jpeg', 
                  uBirthday:'2018-12-11',
                  uEmail:"129312398@qq.com",
                  invState: '1'
          });
          this.name='';
        }
        else{
          this.focusState=true;
        }
      },
      handleDelete(index,row){
        this.collaborators.splice(index,1);
      },
      copy(){
        var Url2 = document.getElementById("nu").value;
        var oInput = document.createElement("input");
        oInput.value = Url2;
        document.body.appendChild(oInput);
        oInput.select();                            // 选择对象
        document.execCommand("Copy");               // 执行浏览器复制命令
        oInput.className = "oInput";
        oInput.style.display = "none";
      }
    },
    mounted(){
      this.$http.get('/api/invitation?pId='+1+'&currentPage='+this.page).then((response)=>{
                this.collaborators=response.data.list;
            })
    }
  }
</script>
