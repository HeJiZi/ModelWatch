<template>
    <transition >
        <router-view/>
    </transition>
</template>

<style>
.in-enter-active, .in-leave-active,.up-enter-active, .up-leave-active{
    transition: transform ease .5s;
}
.in-enter-to{
    transform: translateY(100%);
}

</style>


<script>
import '@/../static/css/admin_login.css'
export default {
    watch: {
        '$route' (to, from) {
            const toDepth = to.path.split('/').length
            const fromDepth = from.path.split('/').length
            this.transitionName = toDepth < fromDepth ? 'slide-right' : 'slide-left'
        }
    }
};
</script>

