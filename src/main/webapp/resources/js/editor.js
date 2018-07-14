var renderer
var camera
var scene
var light
var mesh
var center=new THREE.Object3D()
const app = new Vue({
    el: '#editor',
    data: {
        model:{
            mId:1,
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
        inputValue: ''
    },
    created: function () {

        var obj = this;
        obj.cwidth = window.innerWidth * 0.8;
        obj.rwidth = window.innerWidth * 0.2
        console.log(window.innerHeight)
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
        this.getModel();
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
                var loader=new THREE.JSONLoader();
                var obj=loader.parse(t)
                var mesh=new THREE.Mesh(obj.geometry,obj.materials)
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
            var loader = new THREE.JSONLoader();
            if (this.model.mData == undefined)
                return
            // load a resource
            loader.load(
                // resource URL
                this.model.mData,

                // onLoad callback
                function ( geometry, materials ) {
                    var object = new THREE.Mesh( geometry ,materials);
                    scene.add( object );
                    renderer.render(scene,camera)
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
})

function initThree(obj) {
    var c=document.getElementById('3dcanvas')
    width = c.clientWidth
    height = c.clientHeight
    renderer = new THREE.WebGLRenderer({
        antialias: true,preserveDrawingBuffer:true
    });
    renderer.setSize(width, height);
    renderer.domElement.id='canvas';
    document.getElementById('3dcanvas').appendChild(renderer.domElement);
    renderer.setClearColor(0xF0F0F0, 1.0);
}


function initCamera() {
    camera = new THREE.PerspectiveCamera(45, width/ height, 1, 10000);
    camera.position.x = 1500;
    camera.position.y = 500;
    camera.position.z = 100;
    camera.up.x = 0;
    camera.up.y = 1;
    camera.up.z = 0;
    camera.lookAt(0, 0, 0);
}


function initScene() {
    scene = new THREE.Scene();
}


function initLight() {
    light = new THREE.DirectionalLight(0xFFFFFF, 1);
    // 位置不同，方向光作用于物体的面也不同，看到的物体各个面的颜色也不一样
    ambientLight=new THREE.AmbientLight({color:0xFFFFFF},0.3)
    light.position.set(400, 300, 200);
    scene.add(light);
    scene.add(ambientLight)
}



function initHelper() {
    gridHelper = new THREE.GridHelper( 1000, 20);
    scene.add( gridHelper );

    // cameraHelper=new THREE.CameraHelper(camera);
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
                mouseTranslate(event,mesh)
            }
            break;
        case 2:
            temp.onmousemove = function (event) {
                // if(selectObj){
                //     mouseSurround(event, selectObj.position)
                // }
                // else{
                //     mouseSurround(event,mouse)
                // }
                mouseSurround(event,center.position)

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