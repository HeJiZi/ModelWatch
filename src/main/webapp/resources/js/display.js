var renderer
var camera
var scene
var light
var mesh
var selectmar;
var surroundObj;
var selectObj;
var gridHelper;
var raycaster=new THREE.Raycaster()
var mouse=new THREE.Vector2()
var center=new THREE.Object3D()
const viewapp = new Vue({
    el: '#view',
    data: {
        width: 0,
        height: 0,
        opacity: 0,
        transform: "translateX(-300px)",
        switch_active: false,
        components:[{
            // label: '一级 1',
            // children: [{
            //   label: '二级 1-1',
            //   children: [{
            //     label: '三级 1-1-1'
            //   }]
            // }]
            label:'球体',
            children:[{
                label:'测试样例'
            }],
        },
        {
            label:'长方体'
        }],
        defaultProps: {
            children: 'children',
            label: 'label'
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
    },
    mounted: function () {
        this.init3D();
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
        jump: function () {
            window.location.href = '../';
        },
        init3D: function () {

            initThree(this);
            initCamera();
            initScene();
            initLight();
            initObject();
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
                
                intersects[0].object.material=new THREE.ShaderMaterial({
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

                intersects[0].object.material=new THREE.ShaderMaterial({
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
})

function initThree(obj) {

    width = window.innerWidth;
    height = window.innerHeight
    renderer = new THREE.WebGLRenderer({
        antialias: true
    });
    renderer.setSize(width, height);
    document.getElementById('3dcanvas').appendChild(renderer.domElement);
    renderer.setClearColor(0xF0F0F0, 1.0);
}


function initCamera() {
    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 10000);
    camera.position.x = 15;
    camera.position.y = 5;
    camera.position.z = 10;
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

function initObject(){
    // instantiate a loader
    var loader = new THREE.JSONLoader();

    // load a resource
    loader.load(
        // resource URL
        '/resources/upload/model/marmelab.json',

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
}

function initHelper() {
    gridHelper = new THREE.GridHelper( 1000, 20);
    scene.add( gridHelper );

    // cameraHelper=new THREE.CameraHelper(camera);
    // scene.add(cameraHelper)
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