const projects=new Vue({
    el:"#a-middle",
    data:{
        dialogVisible: false,
        modelName: '',
        projects:[
            {name:'预览',time:'2018/04/06',preview_url:'../../resources/images/9.jpeg'},
            {name:'预览',time:'2018/04/06',preview_url:'../../resources/images/5.jpeg'},
            {name:'预览',time:'2018/04/06',preview_url:'../../resources/images/12.jpeg'},
            {name:'预览',time:'2018/04/06',preview_url:'../../resources/images/temp.jpeg'},
            {name:'预览',time:'2018/04/06',preview_url:'../../resources/images/10.jpeg'},
            {name:'预览',time:'2018/04/06',preview_url:'../../resources/images/11.jpeg'},

        ],
    },
    methods: {
        addModel(){
            var url=window.location.href;
            var id=url.substr(url.lastIndexOf('/')+1,url.length);
            console.log(this.modelName)
            this.$http.post('/model',{
                    mName: this.modelName,
                    project:{
                        pId:id
                    }
                }).then((response)=>{
                if (response.data){
                    this.dialogVisible = false
                    console.log('添加模型成功！')

                }
            })
        },
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
        }
    }
})