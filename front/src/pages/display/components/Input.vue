<template>
    <div ref="input_container" class="input_container">
        <div ref="toolbar" class="toolbar" style="height:25px;border: 1px;border-style: solid; border-color: #f1f1f1; background-color:#f1f1f1">

        </div>
        <div ref="textplace" class="textplace" 
            style="height:100px;max-height:100px;border: 1px;border-style: solid; border-color: #f1f1f1;">
            
        </div> 
        
    </div>


</template>
<script>
import E from 'wangeditor'
export default {
    // props:['newComments'],
    mounted() {
        let editor = new E(this.$refs.toolbar, this.$refs.textplace)
        
        editor.customConfig.onchange = (html) => {
            this.newComment = html 
        }

        editor.customConfig.menus = [
            'link',  // 插入链接
            'emoticon',  // 表情
            'undo',  // 撤销
            'redo'  // 重复
        ]
        editor.create();

    },

    data() {
        return {
            newComment: "",
        }
    },

    methods: {
        getContent() {
            // alert(this.newComment);
            this.$emit("newContentListener", this.newComment);
        }

    }
}
</script>

<style>

</style>
