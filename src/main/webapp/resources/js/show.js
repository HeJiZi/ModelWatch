const projects=new Vue({
    el:"#a-bottom",
    data:{
        models:[],
    },
    methods: {
        getAllModels(){
            this.$http.get('/model').then((response)=>{
                this.models = response.data
            })
        }
    },
    created(){
        this.getAllModels()
    }
})