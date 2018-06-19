const zone=new Vue({
    el:"#zone",
    data:{
        projects:[],
        subPros:[],
        markModels:[],
    },
    created:function () {
        this.$http.get('/zone/JoinPros/'+1).then((response) => {

            this.projects=response.data;
        }, (response) => {
            // 响应错误回调
        });
        this.$http.get('/zone/SubPros/'+1).then((response) => {

            this.subPros=response.data;
        }, (response) => {
            // 响应错误回调
        });
        this.$http.get('/zone/MarkModels/'+1).then((response) => {

            this.markModels=response.data;
        }, (response) => {
            // 响应错误回调
        });

    }
})
