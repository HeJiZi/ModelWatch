<template>
    <article>
        <div class="divInLogManager">
            <el-button type="primary" v-show="!show_create_log" @click="show_create_log = true">新建日志</el-button>
            <el-button type="primary" v-show="!show_search_log" @click="show_search_log = true">搜索日志</el-button>
        </div>
        <div class="divInLogManager">
            <el-collapse-transition>
                <fieldset v-show="show_create_log" style="border:4px #fff groove;border-radius: 10px; ">
                    <legend style="font-size:1.2em;color:grey">新建日志:</legend>
                    <el-input
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6}"
                        placeholder="请输入日志内容"
                    ></el-input>
                </fieldset>
            </el-collapse-transition>
            <el-button
                class="buttonInLogManager"
                type="primary"
                v-show="show_create_log"
                @click="confirm_create_log"
            >上传</el-button>
            <el-button
                class="buttonInLogManager"
                type="danger"
                v-show="show_create_log"
                @click="cancel_create_log"
            >取消</el-button>
        </div>
        <div class="divInLogManager">

            <el-collapse-transition>
                <fieldset v-show="show_search_log"   style="border:4px #fff groove;border-radius: 10px; ">
                    <legend style="font-size:1.2em;color:grey">搜索日志:</legend>
                    <div class="divInLogManager">
                        <div class="divInLogManager">
                            时间：
                            <el-date-picker
                                v-model="pickedTime"
                                type="datetimerange"
                                :picker-options="timePickerOptions"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                                align="right"
                            ></el-date-picker> 
                        </div>

                        <div class="divInLogManager">
                            内容：
                            <el-input
                                v-model="searchContent"
                                style="width:70%;"
                                type="textarea"
                                :autosize="{ minRows:1, maxRows: 4}"
                                placeholder="请输入日志内容" 
                                autocomplete="off"
                            ></el-input>
                        </div>
                        <div class="divInLogManager">
                            用户：
                            <el-input
                                v-model="searchUser"
                                style="width:70%;"  
                                type="text"
                                placeholder="请输入用户名" 
                                autocomplete="off"
                            ></el-input>
                        </div>
                        <div class="divInLogManager">
                            模型：
                            <el-input
                                v-model="searchModel"
                                style="width:70%;" 
                                type="text" 
                                placeholder="请输入模型名" 
                                autocomplete="off"
                            ></el-input>
                        </div>
                        <div>
                            <el-button
                                class="buttonInLogManager"
                                icon="el-icon-search" 
                                round
                                @click="search_log();"
                            >搜索</el-button>
                            <el-button
                                class="buttonInLogManager"
                                type="danger"
                                round
                                v-show="show_search_log"
                                @click="show_search_log=false"
                            >取消</el-button>
                        </div>
                    </div>
                </fieldset>
            </el-collapse-transition>
        </div>
        <div class="divInLogManager">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="currentPageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="logs.length"
            ></el-pagination>
        </div>

        <el-row class="divInLogManager" v-for="(log,index) in get_logs()" :key="log.id">
            <el-card :body-style="{ padding: '0px',width: '70vw'}">
                <div class="divInLogManager">
                    <span
                        @click="read_log_content(log.content);"
                        style="cursor:pointer; display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;"
                    >{{log.content}}+{{index}}</span>
                </div>

                <div style="margin:5px 10px 5px 10px;">
                    <hr>
                    <span style="color:grey;font-size:0.8em">涉及模型：</span>
                    <a
                        style="text-decoration:none;out-line: none;color:#409EFF;margin:5px;"
                        v-bind:href="'/model/'+model"
                        v-for="model in log.models"
                        :key="model.index"
                    >{{model}}</a>
                    
                    <span style="color:grey;font-size:0.8em；margin-left:10px;">创建者：</span>
                    <a
                        style="text-decoration:none;out-line: none;color:#409EFF;margin:5px;"
                        v-bind:href="'/user/'+log.user" 
                    >{{log.user}}</a>
                </div>

                <el-button
                    class="buttonInLogManager"
                    type="primary"
                    icon="el-icon-edit"
                    circle
                    size="small"
                    @click="edit_log(log);"
                ></el-button>
                <el-button
                    class="buttonInLogManager"
                    @click="delete_log(log);"
                    type="danger"
                    size="small"
                    icon="el-icon-delete"
                    circle
                ></el-button>
                <time style="float:right;margin:10px;">{{ log.time.toLocaleString() }}</time>
            </el-card>
        </el-row>
        <!-- 编辑日志内容的Form -->
        <el-dialog title="编辑日志内容" :visible.sync="editContentFormVisible">
            <el-form :model="currentEditLog" ref="logForm">
                <el-form-item>
                    <el-input
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6}"
                        placeholder="请输入日志内容"
                        v-model="currentEditLog.content"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button
                        type="primary"
                        @click="editContentFormVisible = false;submitForm('ruleForm');"
                    >立即创建</el-button>
                    <el-button @click="editContentFormVisible = false;resetForm('ruleForm');">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <!-- 编辑日志内容的Form -->
    </article>
</template>

<style>
.buttonInLogManager {
    margin: 5px;
}
.divInLogManager {
    margin: 15px;
}
</style>


<script>
export default {
    data() {
        return {
            show_create_log: false, 
            show_search_log: false,
            currentPage: 1,
            currentPageSize: 10,
            fakeData: [
                {
                    id: 1,
                    content:
                        "1建了一个超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级厉害的模型",
                    models: ["模型A", "模型B", "模型c"],
                    user: "asd",
                    project: "项目1",
                    time: new Date()
                },
                {
                    id: 2,
                    content:
                        "2修改了一个超级超级超级超级超级超级超级超级超级超级厉害的模型",
                    models: ["模型A"],
                    user: "hejizi", 
                    project: "项目1",
                    time:  new Date()
                },
                {
                    id: 3,
                    content: "3修改了一个超级厉害的模型",
                    models: ["模型c"],
                    user: "hejizi",
                    project: "项目2", 
                    time:  new Date()
                },
                {
                    id: 4,
                    content: "4修改了一个超级厉害的模型",
                    models: ["模型A", "模型B"],
                    user: "hejizi",
                    project: "项目2", 
                    time:  new Date()
                },
                {
                    id: 5,
                    content:
                        "5建了一个超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级厉害的模型",
                    models: ["模型A", "模型B", "模型c"],
                    user: "hejizi",
                    project: "项目1", 
                    time:  new Date()
                },
                {
                    id: 6,
                    content:
                        "6修改了一个超级超级超级超级超级超级超级超级超级超级厉害的模型",
                    models: ["模型A"],
                    user: "hejizi",
                    project: "项目1", 
                    time:  new Date()  
                },
                {
                    id: 7,
                    content: "7修改了一个超级厉害的模型",
                    models: ["模型c"],
                    user: "hejizi",
                    project: "项目2", 
                    time:  new Date()  
                },
                {
                    id: 8,
                    content: "8修改了一个超级厉害的模型",
                    models: ["模型A", "模型B"],
                    user: "hejizi",
                    project: "项目2", 
                    time:  new Date()
                },
                {
                    id: 9,
                    content:
                        "9建了一个超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级厉害的模型",
                    models: ["模型A", "模型B", "模型c"],
                    user: "hejizi",
                    project: "项目1", 
                    time:  new Date()  
                },
                {
                    id: 10,
                    content:
                        "10修改了一个超级超级超级超级超级超级超级超级超级超级厉害的模型",
                    models: ["模型A"],
                    user: "hejizi",
                    project: "项目1", 
                    time:  new Date()  
                },
                {
                    id: 11,
                    content: "11修改了一个超级厉害的模型",
                    models: ["模型c"],
                    user: "hejizi",
                    project: "项目2", 
                    time:  new Date()  
                },
                {
                    id: 12,
                    content: "12修改了一个超级厉害的模型",
                    models: ["模型A", "模型B"],
                    user: "hejizi",
                    project: "项目2", 
                    time:  new Date()  
                }
            ],
            logs: [],
            currentEditLog: { id: 0, content: "" },
            editContentFormVisible: false,
            currentUser: {
                id:1,
                name:"hejizi"
            },
            timePickerOptions: {
                shortcuts: [
                    {
                        text: "最近一周",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(
                                start.getTime() - 3600 * 1000 * 24 * 7
                            );
                            picker.$emit("pick", [start, end]);
                        }
                    },
                    {
                        text: "最近一个月",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(
                                start.getTime() - 3600 * 1000 * 24 * 30
                            );
                            picker.$emit("pick", [start, end]);
                        }
                    },
                    {
                        text: "最近三个月",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(
                                start.getTime() - 3600 * 1000 * 24 * 90
                            );
                            picker.$emit("pick", [start, end]);
                        }
                    }
                ]
            },
            pickedTime: "",
            searchContent: "",
            searchUser: "",
            searchModel: ""
        };
    },
    methods: {
        get_logs() {
            var startPage = (this.currentPage - 1) * this.currentPageSize;

            this.logs = this.fakeData;
            return this.logs.slice(startPage, startPage + this.currentPageSize);
        },
        edit_log(log) {
            this.editContentFormVisible = true;
            this.currentEditLog = log;
        },
        delete_log(log) {
            this.$confirm("此操作将永久删除该日志, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$message({
                        type: "success",
                        message: "删除成功!"
                    });
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消删除"
                    });
                });
        },
        confirm_create_log() {
            this.$confirm("此操作将上传该日志, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
                beforeClose: (action, instance, done) => {
                    if (action === "confirm") {
                        instance.confirmButtonLoading = true;
                        instance.confirmButtonText = "上传中...";
                        setTimeout(() => {
                            done();
                            setTimeout(() => {
                                instance.confirmButtonLoading = false;
                            }, 300);
                        }, 3000);
                    } else {
                        done();
                    }
                }
            })
                .then(() => {
                    this.show_create_log = false;
                    this.$message({
                        type: "success",
                        message: "上传成功!"
                    });
                })
                .catch(() => {});
        },
        cancel_create_log() {
            this.$confirm("此操作将取消创建该日志, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.show_create_log = false;
                    this.$message({
                        type: "info",
                        message: "已取消创建"
                    });
                })
                .catch(() => {});
        },
        read_log_content(content) {
            const h = this.$createElement;
            this.$msgbox({
                title: "日志内容",
                message: h("p", null, [
                    h("span", { style: "color: black" }, content)
                ])
            })
                .then(() => {
                    this.show_create_log = false;
                    this.$message({
                        type: "info",
                        message: "已取消创建"
                    });
                })
                .catch(() => {});
        },
        submitForm(formName) {
            this.$refs[formName].validate(valid => {
                if (valid) {
                    alert("提交成功!");
                } else {
                    console.log("提交出错!");
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        handleSizeChange(val) {
            this.currentPageSize = val;

            var startPage = (this.currentPage - 1) * this.currentPageSize;

            this.logs.slice(startPage, startPage + this.currentPageSize);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            var startPage = (this.currentPage - 1) * this.currentPageSize;

            this.logs.slice(startPage, startPage + this.currentPageSize);
        },
        search_log(){ 
            // var tmp = this.get_logs().concat();
            // this.logs.splice(0,this.logs.length);
            // if(this.pickedTime != null && this.pickedTime[0] != null ){
            //     for(var i=0; i < tmp.length; i++){ 
            //         if(tmp[i].time >= this.pickedTime[0] && tmp[i].time <= this.pickedTime[1]){
            //             this.logs.push(tmp[i]);
            //             tmp.splice(i,1);
            //             i = i-1;
            //         }
            //     }
            // }
            // if(this.searchContent != null && this.searchContent != ""){
            //     for(var i=0; i < tmp.length; i++){ 
            //         if(tmp[i].content.indexOf(this.searchContent) != -1){
            //             this.logs.push(tmp[i]);
            //             tmp.splice(i,1);
            //             i = i-1;
            //         }
            //     }
            // }  
            //         console.log(this.searchUser);
            //         console.log(tmp);
            // if(this.searchUser != null && this.searchUser != ""){
            //     for(var i=0; i < tmp.length; i++){
            //         if(tmp[i].user.indexOf(this.searchUser) != -1){
            //         console.log(tmp[i] ); 
            //             this.logs.push(tmp[i]);
            //             tmp.splice(i,1);
            //             i = i-1;
            //         }
            //     }
            // } 
            // if(this.searchModel != null && this.searchModel != ""){
            //     for(var i=0; i < tmp.length; i++){  
            //         for(var j=0; j < tmp[i].models.length; j++){ 
            //             if(tmp[i].models[j].indexOf(this.searchModel) != -1){
                            
            //         console.log(tmp[i] ); 
            //                 this.logs.push(tmp[i]);
            //                 tmp.splice(i,1);
            //                 i = i-1;
            //             }
            //         }
            //     }
            // }  
            
            // function sortId(a,b){  
            //     return a.id-b.id  
            // }
            // result.sort(sortId);
        }
    }
};
</script>
