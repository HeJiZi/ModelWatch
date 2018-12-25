<template>
    <div style="background: #731919;height:100%;width:100%;margin:0px">
        <section style="display: flex;height: 10%;align-items: flex-start;">
            <div @click="$router.push({path:'/',replace:true})" class="jumpButton signup">
                <div class="el-icon-arrow-down"/>
                <span>
                    登录
                </span>
            </div>
        </section>        
        <section id="login" style="position:relative;top:-5%;">
        <div id = "up-titleCanvas"></div>
        <div class="login_box">
            <div class="title">ModelWatch</div>
            <div class="input">
            <el-input v-model="username" placeholder="用户名"></el-input>
            <el-input v-model="password" type="password" placeholder="密码"></el-input>
            <el-input v-model="rePassword" type="password" placeholder="请重新输入"></el-input>
            <el-button @click="signup" class="signup" round size="mini">注册</el-button>
            </div>
        </div>
        </section>

    </div>
</template>

<script>    
var rightRenderer;
var rightCamera;
var rightScene;
var rightCube;
var width,height;
var globalID

function m_initThree() {

    var titleCanvas = document.getElementById('up-titleCanvas');
    width = titleCanvas.clientWidth;
    height = titleCanvas.clientHeight;
    rightRenderer = new THREE.WebGLRenderer();
    rightRenderer.setSize(width, height);
    titleCanvas.appendChild(rightRenderer.domElement);
    rightRenderer.setClearColor(0x731919, 1.0);
}


function m_initCamera() {
    rightCamera = new THREE.PerspectiveCamera(75, width / height, 0.1, 1000);
    rightCamera.position.z = 5;
}

function m_initScene() {
    rightScene = new THREE.Scene();
}


function m_initLight() {
    // 位置不同，方向光作用于物体的面也不同，看到的物体各个面的颜色也不一样
    var light = new THREE.AmbientLight({ color: 0xFFFFFF }, 1)
    rightScene.add(light)
}

function m_initObject(){
    var geometry = new THREE.CubeGeometry(3,3,3); 
    var material = new THREE.MeshBasicMaterial({color: 0xffffff});
    rightCube = new THREE.Mesh(geometry, material); 
    rightScene.add(rightCube);
}

function m_render()
{
    globalID=requestAnimationFrame(m_render);
    rightCube.rotation.y += 0.05;

    rightRenderer.render(rightScene, rightCamera);
}
function m_threeStart(){
    m_initThree();
    m_initCamera();
    m_initScene();
    m_initLight();
    m_initObject();
    m_render();

}

export default {
    data() {
        return{
            username: '',
            password: '',
            rePassword:'',
        }
    },
    mounted(){
        m_threeStart();
    },
    methods:{
        signup(){
            if(this.rePassword!=this.password){
                alert("输入的密码不一致")
                return;
            }
            this.$http.post('/api/user/signup',{
                uUsername:this.username,
                uPassword:this.password,
            }).then((response)=>{
                if (response.data == true) this.$router.push('/');
                else alert("用户名已存在");
            })
        }
    },
    destroyed(){
        cancelAnimationFrame(globalID);
    }
};
</script>

