const profile=new Vue({
    el:'#profile',
    data:{
        user:'',
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
            this.$http.post(
                '/zone/data',
                // 请求体中要发送给服务端数据
                this.user,
                {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }
            ).then((response)=>{
                console.log(response.data);
            });
        }
    }
})