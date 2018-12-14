<template>
    <div style="width:40%;background: #12338c;">
        <section id="login">
        <div id = "titleCanvas"></div>
        <div class="login_box">
            <div class="title">ModelWatch</div>
            <div class="input">
            <el-input v-model="username" placeholder="用户名"></el-input>
            <el-input v-model="password" type="password" placeholder="密码"></el-input>
            <el-button @click="login"  round size="mini">登录</el-button>
            </div>
        </div>
        </section>
        <section style="display: flex;height: 10%;align-items: flex-end;">
            <div @click="$router.push({path:'/up'})" class="jumpButton login">
                <div  class="el-icon-arrow-up"/>
                <span>
                    注册
                </span>
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

    var titleCanvas = document.getElementById('titleCanvas');
    width = titleCanvas.clientWidth;
    height = titleCanvas.clientHeight;
    rightRenderer = new THREE.WebGLRenderer();
    rightRenderer.setSize(width, height);
    titleCanvas.appendChild(rightRenderer.domElement);
    rightRenderer.setClearColor(0x12338c, 1.0);
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
        }
    },
    mounted(){
        m_threeStart();
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
    },
    destroyed(){
        cancelAnimationFrame(globalID);
    }
};
</script>

