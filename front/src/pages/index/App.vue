<template>
    <div >
        <mw-nav/>
        <router-view/>
    </div>
</template>
<script>
import mwNav from '@/WatchComponents/ModelWatchNav.vue'
export default {
    components:{mwNav}
}
</script>
