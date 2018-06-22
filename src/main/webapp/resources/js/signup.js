/**
 * Created by Colossus on 2018/1/21.
 */
var button=document.getElementById("button");
button.onmouseover=function(){button_over(this)};
button.onmousedown=function(){button_down(this)};
button.onmouseup=function(){button_up(this)};
button.onmouseleave=function(){button_left(this)};
button.onclick=function(){button_click(this)};
var usernameText=document.getElementById("username");
usernameText.onblur=function(){usernameValidate(this,"用户名不能为空")};
var passwordText=document.getElementById("password");
passwordText.onblur=function(){passwordValidate(this,"密码不能为空")};
var rePasswordText=document.getElementById("rePassword");
rePasswordText.onblur=function(){rePasswordValidate(this,"输入的密码不一致")};
var switch_button=document.getElementById("switch");
switch_button.onmouseover=function(){switch_on(this)};
switch_button.onmouseleave=function(){switch_left(this)};
switch_button.onclick=function(){switch_click(this)};

function button_over(obj){
    obj.style.backgroundColor="rgb(117, 117, 241)";
}
function button_down(obj){
    obj.style.backgroundColor="rgb(10, 10, 121)";
}
function button_up(obj){
    obj.style.backgroundColor="rgb(89, 89, 255)";
}
function button_left(obj){
    obj.style.backgroundColor="rgb(89, 89, 255)";
}
function button_click(obj){
    if(obj.value==("注册")){
        register();
    }
    else{
        login();
    }
}
function register(){
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    var rePassword=document.getElementById("rePassword").value;
    if(username==""||password=="") return;
    if(!password==rePassword) return;
    var xmlhttp= new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
            if(xmlhttp.responseText=="false"){
                var label=document.getElementById("username_erro");
                label.innerHTML="用户名已存在";
            }
            else{
                alert("注册成功，请登录");
                label.innerHTML="";
            }
		}
	}
    xmlhttp.open("POST","/user/signup",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("username="+username+"&password="+password);
}
function login(){
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    if(username==""||password=="") return;
    var xmlhttp= new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
            if(xmlhttp.responseText=="true"){
                window.location.href="show";
            }
            else{
                var label=document.getElementById("username_erro");
                label.innerHTML="用户名或密码错误";
            }
		}
	}
    xmlhttp.open("POST","/user/login",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("username="+username+"&password="+password);
}
function usernameValidate(obj,erroMes){
    var label=document.getElementById("username_erro");
    if(obj.value==""){
        label.innerHTML=erroMes;
    }
    else{
        label.innerHTML="";
    }
}
function  passwordValidate(obj,erroMes){
    var label=document.getElementById("password_erro");
    if(obj.value==""){
        label.innerHTML=erroMes;
    }
    else{
        label.innerHTML="";
    }
}
function rePasswordValidate(obj,erroMes){
    var label=document.getElementById("rePassword_erro");
    var password=document.getElementById("password");
    if(obj.value!=password.value){
        label.innerHTML=erroMes;
    }
    else{
        label.innerHTML="";
    }
}
function switch_on(obj){
    obj.className="switch_on";
}
function switch_left(obj){
    obj.className="switch";
}
function switch_click(obj){
    var subTitle=document.getElementById("subTitle");
    if(obj.innerHTML=="登录"){
        subTitle.innerHTML="点击登录,加入我们的精彩世界";
        obj.innerHTML="注册";
        button.value="登录";
        var reP=document.getElementById("re_content");
        reP.parentNode.removeChild(reP);

        document.getElementById("switch_head").innerHTML="没有账号?&nbsp&nbsp";
    }
    else{
        subTitle.innerHTML="点击注册,加入我们的精彩世界";
        obj.innerHTML="登录";
        button.value="注册";

        var outdiv=document.createElement("div");
        outdiv.className="password_content";
        outdiv.id="re_content";
        var middiv=document.createElement("div");
        middiv.className="text_content";
        var input=document.createElement("input");
        input.setAttribute("class","inputText");
        input.setAttribute("type","password");
        input.setAttribute("value","");
        input.setAttribute("placeholder","再次输入密码");
        input.setAttribute("id","rePassword");
        input.onblur=function(){rePasswordValidate(this,"输入的密码不一致")};
        var erro=document.createElement("div");
        erro.className="erromask";
        erro.id="rePassword_erro";

        middiv.appendChild(input);
        middiv.appendChild(erro);
        outdiv.appendChild(middiv);
        document.getElementById("form1").appendChild(outdiv);

        document.getElementById("switch_head").innerHTML="已有账号?&nbsp&nbsp";
    }
}
