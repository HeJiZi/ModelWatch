
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

        },
        pushControl: function (event) {
            console.log(event.button)
        }
    }
})
var renderer;
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

var camera;
function initCamera() {
    camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 10000);
    camera.position.x = 600;
    camera.position.y = 0;
    camera.position.z = 600;
    camera.up.x = 0;
    camera.up.y = 1;
    camera.up.z = 0;
    camera.lookAt(0, 0, 0);
}

var scene;
function initScene() {
    scene = new THREE.Scene();
}

var light;
function initLight() {
    light = new THREE.DirectionalLight(0xFFFFFF, 0.5);
    // 位置不同，方向光作用于物体的面也不同，看到的物体各个面的颜色也不一样
    light.position.set(0, 0, 1);
    scene.add(light);
}

var cube;
function initObject() {
    var geometry = new THREE.CubeGeometry(200, 100, 50);
    var material = new THREE.MeshLambertMaterial({ color: 0xFF0000 });
    var mesh = new THREE.Mesh(geometry, material);
    mesh.position = new THREE.Vector3(0, 0, 0);
    scene.add(mesh);
}