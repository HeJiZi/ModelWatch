<template>
    <div v-loading="modelLoading" element-loading-text="正在加载模型..."
        tabIndex="-1" :style="{width:width+'px',height:height+'px'}" style="overflow: hidden">
            <div class="leftbar moveable" :style="{transform:transform}">
                <el-tree :data="components" :props="defaultProps" ></el-tree>
            </div>
            <article id="3dcanvas" style="background: rgb(231, 231, 231);"  @click="cast" @dblclick="dcast">
            </article>

            <div class="rightbar" style="opacity: 1">
                <a title="切换模式" style="cursor:pointer" @click="$router.push({path:`/${$route.params.mId}/editor`})">
                    <div class="mw-icon transbutton"></div>
                </a>
                <div title="收藏" @click="mark">
                    <div class="mw-icon" :class="{lovebutton:!markInfo,dislovebutton:markInfo}"></div>
                </div>
                <a :href="model.mData" download="model">
                    <div title="下载">
                        <div class="mw-icon downloadbutton"></div>
                    </div>
                </a>
            </div>
     </div>
</template>


<script>
import { Raycaster, Vector2, Object3D, PerspectiveCamera, 
        WebGLRenderer, Scene, DirectionalLight, JSONLoader,
        AmbientLight, GridHelper, ShaderMaterial, Mesh} from "three"

import {mousescale,mouseTranslate,mouseSurround} from '@/../static/lib/modelControl.js'
var x,y
var renderer
var camera
var scene
var light
var mesh
var selectmar;
var surroundObj;
var selectObj;
var gridHelper;
var raycaster=new Raycaster()
var mouse=new Vector2()
var center=new Object3D()


function InitEvent(em){

    em.onmousedown = pushControl;
    em.onmouseup = upControl;
    em.onmouseleave = moveclean;
    
    var pressflag = false;

    document.onkeydown = event=>{
        if(event.keyCode == 18&&!pressflag){
            event.preventDefault();
            pressflag = true;
            em.onmousewheel = wheelEvent => {
                wheelEvent.preventDefault();
                mousescale(wheelEvent,camera,scene,renderer);
            }
        }
    }

    document.onkeyup = event => {
        pressflag = false;
        em.onmousewheel = null;
    }
    
}

function initThree(obj) {

    var width = window.innerWidth;
    var height = window.innerHeight
    renderer = new WebGLRenderer({
        antialias: true
    });
    renderer.setSize(width, height);
    var em = document.getElementById('3dcanvas')
    em.appendChild(renderer.domElement);
    InitEvent(em)
    renderer.setClearColor(0xF0F0F0, 1.0);
}


function initCamera() {
    camera = new PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 10000);
    camera.position.x = 1500;
    camera.position.y = 500;
    camera.position.z = 1000;
    camera.up.x = 0;
    camera.up.y = 1;
    camera.up.z = 0;
    camera.lookAt(0, 0, 0);
}


function initScene() {
    scene = new Scene();
}


function initLight() {
    light = new DirectionalLight(0xFFFFFF, 1);
    // 位置不同，方向光作用于物体的面也不同，看到的物体各个面的颜色也不一样
    var ambientLight=new AmbientLight({color:0xFFFFFF},0.3)
    light.position.set(400, 300, 200);
    scene.add(light);
    scene.add(ambientLight)
}



function initHelper() {
    gridHelper = new GridHelper( 1000, 20);
    scene.add( gridHelper );

}

function pushControl(event) {
    var temp = document.getElementById('3dcanvas')
    x = event.clientX
    y = event.clientY
    var bcode = event.button
    switch (bcode) {
        case 0:
            temp.onmousemove = function (event) {
                var pos = mouseTranslate(event,mesh,camera,scene,renderer,x,y,center)
                x = pos.x;
                y = pos.y;
            }
            break;
        case 2:
            temp.onmousemove = function (event) {
                var pos = mouseSurround(event,center.position,camera,scene,renderer,x,y)
                x = pos.x;
                y = pos.y;

            }
            break;

    }

}
function upControl(event) {
    var temp = document.getElementById('3dcanvas')
    temp.onmousemove = null;

}
function moveclean() {
    var target = document.getElementById('3dcanvas')
    target.onmousemove = null;
}

export default {
    data() {
        return{
            modelLoading:true,
            model:{
                mData: ''
            },
            markInfo: false,
            uId: 0,
            width: 0,
            height: 0,
            opacity: 0,
            transform: "translateX(-300px)",
            switch_active: false,
            components:[{
                label:'logo',
                children:[{
                    label:'inner'
                }],
            }],
            defaultProps: {
                children: 'children',
                label: 'label'
            }
        }
    },
    created: function () {
        this.width = window.innerWidth;
        this.height = window.innerHeight;
        var obj = this;
        window.onresize = function () {
            obj.width = window.innerWidth;
            obj.height = window.innerHeight;
            camera.aspect = window.innerWidth / window.innerHeight;
            camera.updateProjectionMatrix();

            renderer.setSize(window.innerWidth, window.innerHeight);
            renderer.render(scene, camera)
        }
        document.oncontextmenu = function () { return false; }
        this.getModel();
    },
    mounted: function(){
    },
    computed: {
        switch_h: function () {
            return this.height - 50;
        },
        switch_w: function () {
            return parseFloat(this.width) / 2;
        }
    },
    methods: {
        initObject:function(){
            // instantiate a loader
            var loader = new JSONLoader();
            if(this.model.mData==undefined){
                this.modelLoading =false;
                return;
            }
            // load a resource
            var obj =this;
            if(obj.$router.app.mesh==null){
                loader.load(
                // resource URL
                this.model.mData,

                // onLoad callback
                function ( geometry, materials ) {
                    var object = new Mesh( geometry ,materials);
                    obj.$router.app.mesh = object;
                    scene.add( object );
                    renderer.render(scene,camera)
                    obj.modelLoading =false;                
                },

                // onProgress callback
                function ( xhr ) {
                    console.log( (xhr.loaded / xhr.total * 100) + '% loaded' );
                },

                // onError callback
                function( err ) {
                    console.log( 'An error happened' );
                }
                );
            }
            else{
                scene.add(obj.$router.app.mesh);
                renderer.render(scene,camera);
                obj.modelLoading =false;                
            }
            
        },
        jump: function () {
            window.location.href = '../';
        },
        getModel:function(){
            var id=this.$route.params.mId
            this.$http.get('/api/model/'+id).then((response)=>{
                this.model=response.data;
                this.init3D();
                this.getMarkInfo()
            })
        },
        mark: function(){
            if (this.markInfo) {
                this.$http.post('/api/mark/deleteMark',{
                    uid:this.uId ,
                    mid: this.model.mId
                },
                {
                    emulateJSON:true
                }).then((response)=>{
                    if (response.data==1){
                        this.markInfo = false
                        console.log('取消收藏成功！')
                    }
                })
            }
            else {
                this.$http.post('/api/mark/addMark',{
                    uid:this.uId ,
                    mid: this.model.mId
                },
                {
                    emulateJSON:true
                }).then((response)=>{
                    if (response.data==1){
                        this.markInfo = true
                        console.log('收藏成功！')
                    }
                })
            }
        },
        getMarkInfo: function(){
            this.$http.get('/api/user').then((response)=>{
                this.uId = response.data.uId
                this.$http.post('/api/mark/selectMark',{
                    uid: this.uId ,
                    mid: this.model.mId
                },
                {
                    emulateJSON:true
                }).then((response)=>{
                    this.markInfo = response.data==1? true:false
                    console.log(this.markInfo)
                })                
            })

        },
        init3D: function () {

            initThree(this);
            initCamera();
            initScene();
            initLight();
            this.initObject();
            initHelper()
            renderer.render(scene, camera)
        },
        cast:function(event){
            mouse.x=(event.clientX/window.innerWidth)*2-1;
            mouse.y=-(event.clientY/window.innerHeight)*2+1;
            raycaster.setFromCamera(mouse,camera);
            var intersects=raycaster.intersectObjects(scene.children)
            if(selectObj){
                selectObj.material=selectmar;
            }
            if(intersects[0]&&intersects[0].object!=gridHelper){
                selectmar=intersects[0].object.material;
                selectObj=intersects[0].object;
                
                this.transform='';
                var color3=intersects[0].object.material.color
                
                intersects[0].object.material=new ShaderMaterial({
                    uniforms: {
                        directLight: { type: 'v3', value: light.position },
                    },
            
                    //加载顶点着色器程序
                    vertexShader: document.getElementById('vertexshader').textContent,
            
                    //加载片元着色器程序
                    fragmentShader: document.getElementById('fragmentshader').textContent,
            
                });//着色器材质对象
                
            }
            else{
                selectObj=null;
                this.transform='translateX(-300px)'
                
            }
            renderer.render(scene,camera)
            
        },
        dcast:function(event){
            mouse.x=(event.clientX/window.innerWidth)*2-1;
            mouse.y=-(event.clientY/window.innerHeight)*2+1;
            raycaster.setFromCamera(mouse,camera);
            var intersects=raycaster.intersectObjects(scene.children)
            if(selectObj){
                selectObj.material=selectmar;
            }
            if(intersects[0]&&intersects[0].object!=gridHelper){
                selectmar=intersects[0].object.material;
                selectObj=intersects[0].object;

                this.transform='';

                intersects[0].object.material=new ShaderMaterial({
                    uniforms: {
                        directLight: { type: 'v3', value: light.position },
                    },

                    //加载顶点着色器程序
                    vertexShader: document.getElementById('vertexshader').textContent,

                    //加载片元着色器程序
                    fragmentShader: document.getElementById('fragmentshader').textContent,

                });//着色器材质对象
                surroundObj=intersects[0].object;
                camera.lookAt(surroundObj.position);
                center.position.copy(surroundObj.position)
                camera.fov=20;
                camera.updateProjectionMatrix()
            }
            else{
                selectObj=null;
                surroundObj=null;
                this.transform='translateX(-300px)'
            }
            renderer.render(scene,camera)

        }

    }
}
</script>

