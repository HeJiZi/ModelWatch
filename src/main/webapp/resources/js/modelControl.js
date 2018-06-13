function mouseSurround(event,target){
    var dx=event.clientX-x;
    var dy=event.clientY-y;
    x=event.clientX
    y=event.clientY

    SurroundY(camera,target.position,dx/100.0)
    SurroundZ(camera,target.position,-dy/100.0)

    renderer.render(scene,camera)
}

function mouseTranslate(event){
    var dx=event.clientX-x;
    var dy=event.clientY-y;
    x=event.clientX
    y=event.clientY
    camera.translateY(dy)
    renderer.render(scene,camera)
}
function mousescale(event){
    var dir=event.wheelDelta
    camera.fov-=dir/120
    camera.updateProjectionMatrix()
    renderer.render(scene,camera)
}

function SurroundY(obj,target,angle){

    
    //转换坐标系
    turnLocal(obj,target)
    var after=obj.position.y
    var m=new THREE.Matrix3();
    m.set(Math.cos(angle),0,-Math.sin(angle),
            0,1,0,
            Math.sin(angle),0,Math.cos(angle))
    obj.position.applyMatrix3(m)


    //坐标转换回世界坐标系
    turnWorld(obj,target)
    obj.lookAt(target)


}
function SurroundZ(obj,target,angle){
    
    
    //转换坐标系
    turnLocal(obj,target)
    var m=new THREE.Matrix3();
    m.set( Math.cos(angle),Math.sin(angle),0,
            -Math.sin(angle),Math.cos(angle),0,
           0,0,1)
    obj.position.applyMatrix3(m)

    //坐标转换回世界坐标系
    turnWorld(obj,target)
    obj.lookAt(target)
}

function turnLocal(obj,target){
    obj.position.x-=target.x;   
    obj.position.y-=target.y;
    obj.position.z-=target.z;
}
function turnWorld(obj,target){
    obj.position.x+=target.x;   
    obj.position.y+=target.y;
    obj.position.z+=target.z;
}