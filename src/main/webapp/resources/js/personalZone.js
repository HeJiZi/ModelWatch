const projects=new Vue({
    el:"#projects",
    created:function(){
        this.$resource('/zone/JoinPros/{uId}', { uId:1 });
    },
    data:{
        projects:[],
    }
})