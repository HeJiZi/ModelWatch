const zone=new Vue({
    el:"#zone",
    data:{
        projects:[],
        subPros:[],
        markModels:[],
        user:'',
    },
    computed:{
    },
    created:function () {
        var url=window.location.href;
        var id=url.substr(url.lastIndexOf('/')+1,url.length);
        console.log(id)
        this.$http.get('/zone/data/'+id).then((response)=>{
            this.user=response.data
        })
        this.$http.get('/zone/JoinPros/'+id).then((response) => {

            this.projects=response.data;
        }, (response) => {
            // 响应错误回调
        });
        this.$http.get('/zone/SubPros/'+id).then((response) => {

            this.subPros=response.data;
        }, (response) => {
            // 响应错误回调
        });
        this.$http.get('/zone/MarkModels/'+id).then((response) => {

            this.markModels=response.data;
        }, (response) => {
            // 响应错误回调
        });

    }
})
