const profile=new Vue({
    el:'#profile',
    data:{
        user:'',
        alertText:'',
        aldis:'none',
        alType:"error",
        disflag:true,
        file:'',
    },
    created:function () {
        this.$http.get('/zone/data/'+1).then((response) => {

            this.user=response.data;
        }, (response) => {
            // 响应错误回调
        });
    },
    methods:{
        updateData:function () {
            var file=this.file;
            var formdata=new FormData();
            formdata.append("file",file);
            formdata.append("user",JSON.stringify(this.user))
            this.$http.put(
                '/zone/data',
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
        varifyPass:function (event) {
            console.log(11);
            if(event.target.value!=this.user.uPassword){
                this.aldis=''
                this.alType="error"
                this.alertText="两次输入的密码不一致"
                this.disflag=true
            }
            else {
                this.alType="success"
                this.alertText=""
                this.disflag=false
            }
        }
        ,
        updatePass:function () {
            this.$http.put(
                '/zone/pass',
                // 请求体中要发送给服务端数据
                {
                        uId:this.user.uId,
                        uPassword:this.user.uPassword
                },
                {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }
            ).then((response)=>{
                alert("更改成功")
            });
        }
        ,
        upload:function(event){

            var oFReader = new FileReader();
            var file=event.target.files[0];
            this.file=file;

            oFReader.readAsDataURL(file);

            var obj=this;

            oFReader.onloadend=function (OFRevent) {
                var src=OFRevent.target.result;
                obj.user.uAvater=src;
            }

        }
    }
})