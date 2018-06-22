const cp=new Vue({
    el:'#profile',
    data:{
        project:{
            pContext:'',
            pPreview:'',
            pName:''
        },
        file:'',
        disFlag:true,
        aspect:''
    },
    computed:{
        width:function(){
            return this.aspect*300.0;
        }
    },
    methods:{
        varifyCanSubmit:function(){
            console.log(this.project.pName!=''&&this.project.pContext!=''&&this.file!='')
            if(this.project.pName!=''&&this.project.pContext!=''&&this.file!='')
                this.disFlag=false;
            else
                this.disFlag=true;
        },
        upload:function (event) {

            var oFReader = new FileReader();
            var file=event.target.files[0];
            this.file=file;

            oFReader.readAsDataURL(file);

            var obj=this;

            oFReader.onloadend=function (OFRevent) {

                var src=OFRevent.target.result;
                obj.project.pPreview=src;

                var img=new Image();
                img.src=src;
                img.onload=function(){
                    obj.aspect=(img.width/img.height);
                }
            }
            this.varifyCanSubmit();
        },
        submit:function () {
            var formdata=new FormData();
            formdata.append("file",this.file);
            formdata.append("project",JSON.stringify(this.project))
            this.$http.post(
                '/project/creMenu',
                // 请求体中要发送给服务端数据
                formdata,
                {
                    processData: false,       //必不可缺
                    contentType: false,
                }
            ).then((response)=>{
                if (response.data=="true"){
                    //页面跳转
                    alert("更改成功")
                }
                else
                    alert("服务器异常，发生错误，请稍后提交")
            });

        }
    }
})