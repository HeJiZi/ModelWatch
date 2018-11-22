<template>
    <div style="display: flex;padding-left: 20px;width: 80%;margin: 0 auto;align-items: center;justify-content: center">
        <div  class="tempAvater" >
            <img :src="avater" width="60px" height="60px">
        </div>
        <div style="width: 50%;">
            <h5 style="margin: 10px 0px;">{{name}}<span style="color: #b9b9b9;">&nbsp;&nbsp;{{time}}</span></h5>
            <p>{{content}}</p>
        </div>
    </div>
</template>

<script>
export default {
    inheritAttrs: false,
    props: ['avater','name','content','time'],
}
</script>
