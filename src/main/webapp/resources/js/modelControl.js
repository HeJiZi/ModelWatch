function mouseSurround(event,target){
    var dx=event.clientX-x;
    var dy=event.clientY-y;
    x=event.clientX
    y=event.clientY

    SurroundY(camera,target.position,dx/200.0)
    SurroundByObjZ(camera,target.position,dy/300.0)

    renderer.render(scene,camera)
}

function mouseTranslate(event,target){
    
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
    var newup=new THREE.Vector3()
    var d=new THREE.Vector3()
    var newz=new THREE.Vector3()
    d.subVectors(obj.position,target)
    newup.copy(obj.up);
    newz.crossVectors(newup,d);
    newz.normalize();
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