<template>
    <article style="margin-right: 10px;margin-bottom: 10px;overflow: hidden;cursor: pointer;" 
        @mouseenter="transform='translateY(-40px)'" @mouseleave="transform='translateY(0px)'" 
        @click="jump" :style="{height:height}">
        <img :height="height" :width="width" :src="img_url">
        <div :style="{transform: transform}" style="width: 100%;height: 35px;background: rgba(0, 0, 0, 0.253);color:white;
        position: relative;z-index: 1;display: flex;align-items: center;font-family: fantasy; transition: transform ease 0.5s;">
            <span style="color: white; font-weight:bold;font-family:inherit; width:30%">&nbsp;&nbsp;{{name}}</span>
            <div style="display: flex;align-items: center;width:70%;flex-direction: row-reverse;">
                <div style="margin-right:10px">{{time}} </div>
                <div style="background-image: url(../resources/images/icons.png);background-position: -344px -407px;background-repeat: no-repeat;
                    height:100%;height:18px;width: 20px;"></div>
            </div>
        </div>
    </article>
</template>

<script>

export default {
    name:'mw-photo',
    inheritAttrs: false,
    props: ['height','name','time','img_url','redirect'],
    data: function () {
        return {
            transform: 'translateY(0px)',
            aspect:0,
        }
    },
    computed:{
        width:function(){
            return this.aspect*parseFloat(this.height);
        }
    },
    methods:{
        jump:function(){
            var obj=this;
            window.open(this.redirect);
        }
    },
    created:function(){
        var img=new Image();
        var obj=this;
        img.src=obj.img_url;
        img.onload=function(){
            obj.aspect=(img.width/img.height);
        }
    },
}
</script>

