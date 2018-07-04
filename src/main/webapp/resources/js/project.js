const projects=new Vue({
    el:"#a-middle",
    data:{
        dialogVisible: false,
        subscribe: false,
        uId: 6,
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
                    window.location.href = '/model/edi/'+response.data
                    console.log('添加模型成功！')
                }
            })
        },
        subscribeProject(){
            var url=window.location.href;
            var id=url.substr(url.lastIndexOf('/')+1,url.length);
            if (!this.subscribe){
                this.$http.post('/subscribe/'+id+'/'+this.uId).then((response)=>{
                    if (response.data){
                        this.subscribe = true
                        console.log('关注项目成功！')
                    }
                })
            }
            else {
                this.$http.delete('/subscribe/'+id+'/'+this.uId).then((response)=>{
                    if (response.data){
                        this.subscribe = false
                        console.log('删除项目成功！')
                    }
                })
            }
        },
        getSubscribe(){
            var url=window.location.href;
            var id=url.substr(url.lastIndexOf('/')+1,url.length);
            this.$http.get('/subscribe/'+id+'/'+this.uId).then((response)=>{
                this.subscribe = response.data==1? true:false
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
        this.$http.get('/user').then((response)=>{
            this.uId = response.data.uId
            this.getSubscribe()
        })
        this.getProjectModels()

    }
})