function mouseSurround(event,target){
    var dx=event.clientX-x;
    var dy=event.clientY-y;
    x=event.clientX
    y=event.clientY

    SurroundY(camera,target,dx/200.0)
    SurroundByObjZ(camera,target,dy/300.0)

    renderer.render(scene,camera)
}

function mouseTranslate(event,target){
    var m4=new THREE.Matrix4()
    var v4=new THREE.Vector4()
    var dx=event.clientX-x;
    var dy=event.clientY-y;
    x=event.clientX
    y=event.clientY

    var v3=new THREE.Vector3(1,0,0)
    v3.applyQuaternion(camera.quaternion)
    m4.set(
            1,0,0,v3.x*(-dx*2),
            0,1,0,v3.y*(-dx*2)+dy,
            0,0,1,v3.z*(-dx*2),
            0,0,0,1
            );
    v4.set(camera.position.x,camera.position.y,camera.position.z,1)

    v4.applyMatrix4(m4)
    // console.log(v3)
    camera.position.set(v4.x,v4.y,v4.z)

    v4.set(center.position.x,center.position.y,center.position.z,1)
    v4.applyMatrix4(m4)
    center.position.set(v4.x,v4.y,v4.z)
    renderer.render(scene,camera)
}
function mousescale(event){
    var dir=event.wheelDelta
    if(camera.fov<=10&&dir>0){
        return
    }
    camera.fov-=dir/240.0
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
function SurroundByObjZ(obj,target,angle){
    turnLocal(obj,target)
    var newz=getLocalZAxis(obj,target)
    var cos=Math.cos(angle)
    var sin=Math.sin(angle)
    var m=new THREE.Matrix3();
    var nx=newz.x;
    var ny=newz.y;
    var nz=newz.z;
    m.set(nx*nx*(1-cos)+cos,nx*ny*(1-cos)+nz*sin,nx*nz*(1-cos)-ny*sin,
            nx*ny*(1-cos)-nz*sin,ny*ny*(1-cos)+cos,ny*nz*(1-cos)+nx*sin,
            nx*nz*(1-cos)+ny*sin,ny*nz*(1-cos)-nx*sin,nz*nz*(1-cos)+cos)
    obj.position.applyMatrix3(m)
    turnWorld(obj,target)
    obj.lookAt(target)
}
function getLocalZAxis(obj,target){
    var newup=new THREE.Vector3()
    var d=new THREE.Vector3()
    var newz=new THREE.Vector3()
    d.subVectors(obj.position,target)
    newup.copy(obj.up);
    newz.crossVectors(newup,d);
    newz.normalize();
    return newz;
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