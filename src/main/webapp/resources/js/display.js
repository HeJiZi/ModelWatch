var renderer
var camera
var scene
var light
var mesh
const viewapp = new Vue({
    el: '#view',
    data: {
        width: 0,
        height: 0,
        opacity: 0,
        transform: "translateX(-300px)",
        switch_active: false,

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
        document.oncontextmenu=function(){return false;}
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
            renderer.render(scene, camera)
        },
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
    camera.position.x = 1500;
    camera.position.y = 500;
    camera.position.z = 1000;
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
    light.position.set(0, 0, 1);
    scene.add(light);
}

function initObject() {
    var geometry = new THREE.CubeGeometry(200, 100, 50);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF0000 });
    mesh = new THREE.Mesh(geometry, material);
    mesh.position.y += 51;

    var g2 = new THREE.PlaneGeometry(800, 1000, 3);
    var ma = new THREE.MeshBasicMaterial({ color: 0xCFCFCF, side: THREE.DoubleSide })
    ma.transparent = true;
    ma.opacity = 0.2
    var plane = new THREE.Mesh(g2, ma)
    plane.rotateX(3.14 / 2)

    var g3 = new THREE.CubeGeometry(50, 50, 50);
    var m3 = new THREE.MeshLambertMaterial({ color: 0xFF0000 });
    sphere = new THREE.Mesh(g3, m3);
    sphere.position.x = 240
    sphere.position.z = 200;
    sphere.position.y = 80
    sphere.up.x = 0;
    sphere.up.y = 1;
    sphere.up.z = 0;

    scene.add(mesh);
    scene.add(plane)
    scene.add(sphere)
}

function pushControl(event) {
    var temp = document.getElementById('3dcanvas')
    x = event.clientX
    y = event.clientY
    var bcode = event.button
    console.log(bcode)
    switch (bcode) {
        case 0: 
            temp.onmousemove = function (event) {
                mouseSurround(event, mesh)
            }
            break;
        case 2:
            temp.onmousemove=function(event){
                mouseTranslate(event,mesh)
            }
        
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