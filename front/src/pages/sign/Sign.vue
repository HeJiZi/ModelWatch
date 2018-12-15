<template>
    <div style="height:100%;position:absolute;width:40%;display:flex;flex-flow:row wrap;overflow:hidden;">
        <transition :name="transitionName">
            <router-view />
        </transition>
    </div>
</template>

<style>
.slide-up-enter-active, .slide-up-leave-active,.slide-down-enter-active, .slide-down-leave-active{
    transition: transform ease .5s;
}
.slide-up-leave-to,.slide-up-enter-to{
    transform: translateY(-100%);
}
.slide-down-enter{
    transform:translateY(-200%)
}
.slide-down-enter-to{
    transform:translateY(-100%)
}
.slide-down-leave-to{
    transform: translateY(100%)
}

</style>


<script>
import '@/../static/css/admin_login.css'

export default {
    data(){
        return{
            transitionName:''
        }
    },
    watch: {
        $route(to, from) {
            console.log(to);
            this.transitionName = to.meta.index > from.meta.index?'slide-up':'slide-down';
        }
    },
};
</script>

