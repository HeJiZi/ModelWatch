<template>
    <article>
        <p @click="test">{{msg}}</p>
    </article>
</template>
<script>
export default {
    data(){
        return{
            msg: '项目总览页面',
        }
    },
    created: function(){
        console.log(this.msg);
    },
    methods:{
        test: function(){
        }
    }
}
</script>