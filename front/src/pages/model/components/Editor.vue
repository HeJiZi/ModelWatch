<template>
    <div>
        <nav class="normalnav">
            <a title="切换模式"  @click="$router.push({path:`/${$route.params.id}`})" class="mw-icon transbutton" style="margin: 0 3px;height: 20px;width:20px;cursor:pointer"></a>
            <a title="项目名" :href="'/project/'+model.project.pId" style="margin-left: 5px">
                <h3>{{model.project.pName}}</h3>
            </a>
            <h3 style="cursor: default;margin-right:10px"> -></h3>
            <h3>
                <el-input
                        class="input-new-tag"
                        v-if="inputVisible"
                        v-model="model.mName"
                        ref="saveTagInput"
                        size="small"
                        @keyup.enter.native="handleInputConfirm"
                        @blur="handleInputConfirm"
                >
                </el-input>
                <el-button v-else class="button-new-tag" size="small" @click="showInput"
                        style="border:none;font-size: 1em;font-weight: bold"
                >{{model.mName}}</el-button>
            </h3>
            <div class="submitButton" @click="upload">上传模型</div>
        </nav>
        <ul class="menubar">
            <label for="temp">文件</label>
            <label>设置</label>
            <label>工具</label>
            <label>渲染</label>
            <label>视图</label>
            <label>动画</label>
            <label>物理模拟</label>
            <label>帮助</label>
        </ul>
        <div class="toolbar">
            <div class="tool">
                <img src="/resources/images/selectButton.png">
            </div>
            <div class="tool">
                <img src="/resources/images/moveButton.png">
            </div>
            <div class="tool">
                <img src="/resources/images/rotateButton.png">
            </div>
            <div class="tool">
                <img src="/resources/images/scaleButton.png">
            </div>
        </div>
        <article v-loading="modelLoading" id="view" class="operation-view">
            <section class="component" :style="{transform:ltrans}">
                <el-tree  :data="components" :props="defaultProps"></el-tree>
            </section>
            <ul class="labels" :style="{transform:ltrans}" @click="label_click">
                <li>{{label_dire}}组件</li>
            </ul>

            <section id="3dcanvas" :style="{height:vheight+'px',width:cwidth+'px'}"
                    onmousewheel="mousescale(event)" onmousedown="pushControl(event)"
                    onmouseup="upControl(event)" onmouseleave="moveclean()">
            </section>
            <section class="creatComp" >
                    <el-tabs :style="{height:vheight+'px',width:rwidth+'px'}" type="border-card">
                            <el-tab-pane label="创建">
                                <el-row style="margin-bottom:10px;">
                                    <el-button size="mini" >&nbsp;&nbsp;球体&nbsp;</el-button>
                                    <el-button size="mini" >圆柱体</el-button>
                                    <el-button size="mini" >长方体</el-button>
                                    <el-button size="mini" >棱锥体</el-button>
                                </el-row >
                                <el-row>
                                    
                                    <el-button size="mini" >胶囊体</el-button>
                                    <el-button size="mini" >棱台体</el-button>
                                </el-row>
                                <input type="file" id="temp" style="visibility: hidden" @change="upObject">
                                
                            </el-tab-pane>
                            <el-tab-pane label="修改">修改</el-tab-pane>
                            <el-tab-pane label="动画">动画</el-tab-pane>
                            <el-tab-pane label="工具">工具</el-tab-pane>
                    </el-tabs>
            </section>
        </article>
    </div>
</template>
<script>
import '|css/main.css'
import '|css/watchUI.css'

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
var width,height
var center=new Object3D()

function initThree(obj) {
    var c=document.getElementById('3dcanvas')
    width = c.clientWidth
    height = c.clientHeight
    renderer = new WebGLRenderer({
        antialias: true,preserveDrawingBuffer:true
    });
    renderer.setSize(width, height);
    renderer.domElement.id='canvas';
    document.getElementById('3dcanvas').appendChild(renderer.domElement);
    renderer.setClearColor(0xF0F0F0, 1.0);
}


function initCamera() {
    camera = new PerspectiveCamera(45, width/ height, 1, 10000);
    camera.position.x = 1500;
    camera.position.y = 500;
    camera.position.z = 100;
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
    var gridHelper = new GridHelper( 1000, 20);
    scene.add( gridHelper );

    // cameraHelper=new CameraHelper(camera);
    // scene.add(cameraHelper)
}

function dataURLtoFile(dataurl, filename) {//将base64转换为文件
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
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
    data(){
        return{
            model:{
                mId:1,
                mData:'/resources/3.json',
                mName:'',
                project:{
                    pId:1,
                    pName:'',
                }
            },
            file:'',
            ltrans: "translateX(-300px)",
            label_dire: '>',
            vheight: '',
            cwidth: '',
            rwidth: '',
            components:[{
                label:'logo',
                children:[{
                    label:'inner'
                }],
            }],
            defaultProps: {
                children: 'children',
                label: 'label'
            },
            inputVisible: false,
            inputValue: '',
            modelLoading:true,       
        }
    },
    created: function () {

        var obj = this;
        obj.cwidth = window.innerWidth * 0.8;
        obj.rwidth = window.innerWidth * 0.2
        obj.vheight = window.innerHeight - 87;

        window.onresize = function () {
            obj.cwidth = window.innerWidth * 0.8;
            obj.rwidth = window.innerHeight * 0.2
            obj.vheight = window.innerHeight - 87;
            // camera.aspect = window.innerWidth / window.innerHeight;
            // camera.updateProjectionMatrix();

            // renderer.setSize(window.innerWidth, window.innerHeight);
            // renderer.render(scene, camera)
        }
        document.oncontextmenu = function () { return false; }
        // this.getModel();
    },
    mounted: function(){
        this.init3D();
    },
    methods: {
        upObject:function (event) {
            var oFReader = new FileReader();
            var file=event.target.files[0];
            this.file=file;

            oFReader.readAsText(file,"utf-8")

            oFReader.onloadend=function (OFRevent) {
                // console.log(OFRevent.target.result);
                var t=JSON.parse(OFRevent.target.result)
                var loader=new JSONLoader();
                var obj=loader.parse(t)
                var mesh=new Mesh(obj.geometry,obj.materials)
                scene.add(mesh);
                renderer.render(scene,camera)
            }
        },
        upload:function(){
            var iurl=document.getElementById('canvas').toDataURL('image/png')
            var image=dataURLtoFile(iurl,"preview.png")


            var formdata=new FormData();
            formdata.append("preview",image)
            formdata.append("file",this.file);

            formdata.append("model",JSON.stringify(this.model))
            this.$http.post(
                '/upmodel',
                // 请求体中要发送给服务端数据
                formdata,
                {
                    processData: false,       //必不可缺
                    contentType: false,
                }
            ).then((response)=>{
                alert("更改成功")
            });
        },
        initObject:function(){
            // instantiate a loader
            var loader = new JSONLoader();
            if (this.model.mData == undefined){
                this.modelLoading =false;  
                return
            }
            var obj =this;
            if(obj.$router.app.mesh==null){
                // load a resource
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
        label_click: function () {
            var lt = this.ltrans;
            this.ltrans = lt == '' ? 'translateX(-300px)' : '';
            var ld = this.label_dire;

            this.label_dire = ld == '<' ? '>' : '<';
        },
        getModel:function(){
            var url=window.location.href;
            var id=url.substr(url.lastIndexOf('/')+1,url.length);
            this.$http.get('/model/'+id).then((response)=>{
                this.model=response.data;
                this.init3D();
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
        handleClose(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },

        showInput() {
            this.inputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },

        handleInputConfirm() {
            this.inputVisible = false;
        }
    }
}
</script>
