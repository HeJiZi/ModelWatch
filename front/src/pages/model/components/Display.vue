<template>
    <div id='display'>
        <div @mouseenter="isActive=true" @mouseleave="isActive=false">
            <div style="z-index: 10000" class="nav-wrapper moveable" :class={active:isActive}>
                <mw-nav></mw-nav>
            </div>
            <div style="height: 60px;opacity:0;position:absolute;width: 100%;"></div>
        </div>
        <router-view></router-view>
        <router-view name ='interact'></router-view>
    </div>
</template>


<script>
import '|css/main.css'
import mwNav from '@/WatchComponents/ModelWatchNav.vue'


export default {
    data(){
        return{
            isActive:false,
        }
    },
    components:{ mwNav }, 
}
</script>

