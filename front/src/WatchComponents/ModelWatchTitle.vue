<template>
    <div style="height: 50px;display: flex;flex-flow: row wrap;align-items: center;justify-content: center;">
        <a :href="url" style="font-size: 30px;font-weight: bold;font-weight:bold;font-family: 'Times New Roman', Times, serif;text-decoration: none; " 
        @mouseenter="color='gray'" @mouseleave="color='black'" :style="{color: color}" >
            {{title}}
        </a>
    </div>
</template>

<script>
export default {
    name:'mw-title',
    props: ['title', 'url'],
    data: function () {
        return {
            color: "black",
        }
    },
}
</script>
