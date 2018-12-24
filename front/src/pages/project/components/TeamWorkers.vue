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
            :trigger-on-focus="false"
             @select="handleSelect"
            :select-when-unmatched="true"
            >
            <template slot-scope="{item}">
              <div style="margin:10px 0px;">
                <div class="avatar" style="width:30px;height:30px">
                  <img :src='serv_root+item.uAvater' width="30px" height="30px" >
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
                      <img :src='serv_root+scope.row.uAvater' width="60px" height="60px" >
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
                @click="handleDelete(scope.$index,scope.row)">删除</el-button>
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
                <el-button size="mini" type="danger" @click="cancelInv(scope.$index,scope.row)">取消邀请</el-button>
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
      :total="totalNum"
      style="margin:0px 350px"
      :current-page.sync="page"
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
  import {serv_root} from '@config'
  export default {
    data() {
      return {
        serv_root:serv_root,
        users: [],
        name:'',
        inputBoxHeight:'0px',
        page:1,
        focusState:false,
        totalNum:0,
        uMail:'',
        collaborators: [
        // {
        //   uId:1,
        //   uUsername: 'HeJiZi',
        //   uSignature:'太阳照常升起',
        //   invTime:'2018-12-07 12:00:00',
        //   uAvater:'/static/images/temp.jpeg',
        //   uEmail:"129312398@qq.com",
        //   uBirthday:'2018-07-05',
        //   invState: '0'
        // }, 
        ]
      };
    },
    methods:{
      querySearch(queryString, cb){  
              this.$http.get('/api/user/'+queryString).then((response)=>{   
                      this.users=response.data.list;
                      cb(this.users);
              })
              .catch((response) => { 
                              alert('查询失败!'); 
                        }); 
      },
      changePage(val){
        this.page=val;
        this.$http.get('/api/invitation?pId='+1+'&currentPage='+this.page).then((response)=>{
                this.collaborators=response.data.list;
            });
      },
      addCollaborators(){
        if(this.users.length==0){
          this.name='';
        }
        if(this.name!=null&&this.name.length!=0){
            this.$http.post('/api/invitation/'+1+'/'+this.name).then((response)=>{
                this.$http.get('/api/invitation?pId='+1+'&currentPage='+this.page).then((response)=>{
                      this.collaborators=response.data.list;
                      this.totalNum=response.data.page.totalNum;
                }); 
                this.$http.get('/api/user/findMail/'+this.name).then((response)=>{
                      this.uMail=response.data.list[0].uEmail;
                      this.$http.post('/api/invitation/sendMail?name='+'HeJiZi'+'&uMail='+this.uMail+'&pId='+1).then((response)=>{
                        if(!response.data){alert("已发送邮件！");}           
                      });
                });
            })
            .catch((response)=>{
                alert("邀请失败！");
            });
        }
      },
      handleSelect(item) {
        this.name=item.uUsername;
      },
      handleDelete(index,user){
          this.$confirm("此操作将删除该协作者, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                  this.$http.delete('/api/invitation/1/'+user.uId) 
                        .then((response) => {   
                            if(response.data!=0){ 
                              this.collaborators.splice(index,1);
                              this.$http.get('/api/invitation?pId='+1+'&currentPage='+this.page).then((response)=>{
                                this.collaborators=response.data.list;
                                this.totalNum=response.data.page.totalNum;
                              });    
                              this.$message.success('成功删除了协作者' + user.uUsername + '!');                       
                            }
                        }) 
                        .catch((response) => { 
                             this.$message.error('删除失败!'); 
                        }); 
              })
              .catch(() => {
                    this.$message.info('已取消删除!'); 
              });      
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
      },
      cancelInv(index,user){
          this.$http.delete('/api/invitation/1/'+user.uId) 
                        .then((response) => {   
                            if(response.data!=0){ 
                              this.collaborators.splice(index,1);
                              this.$http.get('/api/invitation?pId='+1+'&currentPage='+this.page).then((response)=>{
                                this.collaborators=response.data.list;
                                this.totalNum=response.data.page.totalNum;
                              });    
                              this.$message.success('已取消邀请！' + user.uUsername + '!');                       
                            }
                        }) 
                        .catch((response) => { 
                             this.$message.error('取消失败!'); 
                        }); 
      }
    },
    mounted(){
      console.log(serv_root);
      var obj =this;
      this.$http.get('/api/invitation?pId='+obj.$route.params.pId+'&currentPage='+this.page).then((response)=>{
                this.collaborators=response.data.list;
                this.totalNum=response.data.page.totalNum;
            });
    }
  }
</script>
