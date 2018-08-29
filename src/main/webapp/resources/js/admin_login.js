function Username(obj)
{
    var label=document.getElementById("username_erro");
    if(obj.value==""){
        label.innerHTML="用户名不能为空";
    }
    else{
        label.innerHTML="";
    }
}

function Password(obj)
{
    var label=document.getElementById("password_erro");
    if(obj.value==""){
        label.innerHTML="密码不能为空";
    }
    else{
        label.innerHTML="";
    }
}


function Login()
{
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    if(username==""||password=="") return;
    var xmlhttp= new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
            if(xmlhttp.responseText=="true"){
                window.location.href="http://localhost:8080/AdminHome.action";
            }
            else{
                var label=document.getElementById("username_erro");
                label.innerHTML="用户名或密码错误";
            }
		}
	}
    xmlhttp.open("POST","/AdminLogin.action",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("username="+username+"&password="+password);
}