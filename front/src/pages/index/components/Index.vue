<template>
    <div>
        <div id="container">
            <div  id="a-top">
            </div>
            <h1>热门推荐</h1>
            <div id="a-bottom">
                <div class="projects" >
                    <mw-photo  v-for="model in models" :key="model.key" :name="model.mName" :time="model.mCreateTime"  :img_url="model.mPreview" height="250px" :redirect="'/model#/'+model.mId"/>
                </div>
            </div>        
        </div>
    </div>
</template>
<script>
import '@/../static/css/show.css'
import mwPhoto from '@/WatchComponents/ModelWatchPhoto.vue'
import { PerspectiveCamera, WebGLRenderer, Scene, DirectionalLight,MeshBasicMaterial,
        AmbientLight, Mesh,CubeGeometry,CanvasTexture,PlaneGeometry} from "three"

var renderer;
var camera;
var scene;
var cube;
var width,height;
var globalID

function m_initThree() {

    var canvas = document.getElementById('a-top');
    var tiltle = document.getElementById('cover-tiltle');
    width = canvas.clientWidth;
    height = canvas.clientHeight;
    renderer = new WebGLRenderer();
    renderer.setSize(width, height);
    renderer.domElement.style.position = 'absolute';
    renderer.domElement.style.zIndex = '-1';
    canvas.insertBefore(renderer.domElement,tiltle);
    renderer.setClearColor(0x409EFF, 1.0);
}


function m_initCamera() {
    camera = new PerspectiveCamera(75, width / height, 0.1, 1000);
    camera.position.z = 5;
}

function m_initScene() {
    scene = new Scene();
}


function m_initLight() {
    // 位置不同，方向光作用于物体的面也不同，看到的物体各个面的颜色也不一样
    var light = new AmbientLight({ color: 0xFFFFFF }, 1)
    scene.add(light)
}

function m_initObject(){

    function getTextCanvas(text){ 
        var width=800, height=400; 
        var canvas = document.createElement('canvas');
        canvas.width = width;
        canvas.height = height;
        var ctx = canvas.getContext('2d');


        ctx.fillStyle = '#fff'; //设置笔触的颜色
        ctx.font = "bold 200px Arial"; //设置字体
        ctx.fillText(text ,0 ,320); //设置文本内容
        
        return canvas;
    } 


    var geometry = new CubeGeometry(3,3,3); 
    var material = new MeshBasicMaterial({color:0xffffff});

    cube = new Mesh(geometry, material); 
    scene.add(cube);

    var modelTextGeo = new PlaneGeometry(6,3);
    var modelText = new Mesh(modelTextGeo,new MeshBasicMaterial({map:new CanvasTexture(getTextCanvas('Model')),transparent:true}));
    modelText.position.x -= 4.5;
    scene.add(modelText);

    var watchTextGeo = new PlaneGeometry(6,3);
    var watchText = new Mesh(watchTextGeo,new MeshBasicMaterial({map:new CanvasTexture(getTextCanvas('Watch')),transparent:true}));
    watchText.position.x += 6;
    scene.add(watchText);
}

function m_render()
{
    globalID=requestAnimationFrame(m_render);
    cube.rotation.y -= 0.05;

    renderer.render(scene, camera);
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
    data(){
        return{
            models:[
                {mId:1,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
                {mId:2,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
                {mId:3,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
                {mId:4,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
                {mId:5,mName:'logo',mCreateTime:'2018-09-07',mPreview:'/resources/images/1.png'},
            ]
      
        }
    },
    methods:{

    },
    mounted(){
        m_threeStart()
        var obj =this;
        window.onresize = function () {
            width = window.innerWidth;
            camera.aspect = width / height;
            camera.updateProjectionMatrix();

            renderer.setSize(width, height);
            renderer.render(scene, camera)
        }        
    },
    destroyed(){
        cancelAnimationFrame(globalID)
    },
    components:{mwPhoto},
    
}
</script>
