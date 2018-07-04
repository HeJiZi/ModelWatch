const projects=new Vue({
    el:"#a-middle",
    data:{
        dialogVisible: false,
        modelName: '',
        models:[
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
        },
        getProjectModels(){
            var url=window.location.href;
            var id=url.substr(url.lastIndexOf('/')+1,url.length);
            this.$http.get('/project/model/'+id).then((response)=>{
                this.models = response.data
            })
        }
    },
    created(){
        this.getProjectModels()
    }
})