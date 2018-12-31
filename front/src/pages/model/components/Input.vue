<template>
    <div ref="input_container" class="input_container">
        <div ref="textplace" class="textplace" >
        </div>
         <!-- 提示信息与工具条 -->
        <div style="display:flex; width:100%;height: 35px;justify-content: space-between;">
            <div ref="toolbar" class="toolbar"></div>
            <p style="height: 35px;line-height: 35px;color:#b9b9b9; margin-left: 10px;">{{inputTip}}</p>
        </div>
    </div>


</template>
<script>
import E from 'wangeditor'
export default {
    props:['inputTip'],

    mounted() {
        let editor = new E(this.$refs.toolbar, this.$refs.textplace)
        
        editor.customConfig.onchange = (html) => {
            this.newComment = html 
        }

        editor.customConfig.menus = [
            'emoticon',  // 表情
            'link',  // 插入链接
            'undo',  // 撤销
            'redo',  // 重复
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
.textplace {
    height:75px;
    max-height:75px; 
}

.w-e-text:focus {
    background: #ffffff;
    border: 1px;
    border-style: solid; 
    border-color: #409EFF;
} 

.w-e-text {
    overflow-y: auto;
    font-size: 18px;
    padding: 0px;
    border: 1px;
    border-style: solid; 
    border-color: #dddddd;
    border-radius: 10px;
    padding: 0px 20px 0px 20px;
    background: #f3f3f3;
}

.w-e-text p {
    line-height: 1.0;
}

.toolbar {
    width: 150px;
    height: 35px;
    display: flex;
    align-items: center;
    border-bottom: none;
    border-top: none;
    justify-content: space-around;
}

.w-e-text-container .w-e-panel-container {
    border-radius: 5px;
    top: 110px;
    left: 5%;
}
</style>
