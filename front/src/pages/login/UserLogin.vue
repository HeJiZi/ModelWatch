<template>
    <div id="login">
      <div id = "titleCanvas"></div>
      <div class="login_box">
        <div class="title">ModelWatch</div>
        <div class="input">
          <el-input v-model="username" placeholder="用户名"></el-input>
          <el-input v-model="password" type="password" placeholder="密码"></el-input>
          <el-button @click="login" round size="mini">登录</el-button>
        </div>
      </div>
  </div>
</template>

<script>
export default {
    name: 'UserLogin',
    data() {
        return{
            username: '',
            password: '',
        }
    },
    methods:{
        login(){
            this.$http.post('/admin',{
                aUsername:this.username,
                aPassword:this.password,
            }).then((response)=>{
                if (response.data == true) window.location.href = '/managers';
                else alert("用户名或密码错误");
            })
        }
    }
};
</script>

