<template>
    <article>
        <div class="divInLogManager">
            <el-button type="primary" v-show="!show_create_log" @click="show_create_log = true">新建日志</el-button>
            <el-button type="primary" v-show="!show_search_log" @click="show_search_log = true">搜索日志</el-button>
        </div>
        <div class="divInLogManager">
            <el-collapse-transition>
                <fieldset
                    v-show="show_create_log"
                    style="border:4px #fff groove;border-radius: 10px; "
                >
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
                <fieldset
                    v-show="show_search_log"
                    style="border:4px #fff groove;border-radius: 10px; "
                >
                    <legend style="font-size:1.2em;color:grey">搜索日志:</legend>
                    <div class="divInLogManager">
                        <div class="divInLogManager">时间：
                            <el-date-picker
                                v-model="searchConditions.timeRange"
                                type="datetimerange"
                                :picker-options="timePickerOptions"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                                align="right"
                            ></el-date-picker>
                        </div>
                        <div class="divInLogManager">用户：
                            <el-input
                                v-model="searchConditions.uUsername"
                                style="width:70%;"
                                type="text"
                                placeholder="请输入用户名"
                                autocomplete="off"
                            ></el-input>
                        </div>
                        <div class="divInLogManager">模型：
                            <el-input
                                v-model="searchConditions.mName"
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

        <el-row class="divInLogManager" v-for="log in logs" :key="log.lId">
            <el-card :body-style="{ padding: '0px',width: '70vw'}">
                <div class="divInLogManager">
                    <span
                        @click="read_log_context(log.lContext);"
                        style="cursor:pointer; display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 3;overflow: hidden;"
                    >{{log.lContext}}</span>
                </div>

                <div style="margin:5px 10px 5px 10px;">
                    <hr>
                    <span style="color:grey;font-size:0.8em">模型：</span>
                    <a
                        style="text-decoration:none;out-line: none;color:#409EFF;margin:5px;"
                        v-bind:href="'/model/'+log.model"
                    >{{log.model.mName}}</a>
                    
                    <span style="color:grey;font-size:0.8em；margin-left:10px;">创建者：</span>
                    <a
                        style="text-decoration:none;out-line: none;color:#409EFF;margin:5px;"
                        v-bind:href="'/user/'+log.user"
                    >{{log.user.uUsername}}</a>
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
                <time style="float:right;margin:10px;">{{ new Date(log.lTime).toLocaleString() }}</time>
            </el-card>
        </el-row>
        <!-- 编辑日志内容的Form -->
        <el-dialog title="编辑日志内容" :visible.sync="editFormVisible">
            <el-form :model="editForm" ref="editForm" >
                <el-form-item>
                    <el-input
                        v-model="editForm.lContext"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6}"
                        placeholder="请输入日志内容" 
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button
                        type="primary"
                        @click="editFormVisible = false;submiteditForm();"
                    >确认修改</el-button>
                    <el-button @click="reseteditForm();">重置</el-button>
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
            getLogsBypPidUrl: "/api/project/log/",
            searchLogsUrl: "api/log",
            deleteLogUrl: "api/log",
            updateLogUrl: "api/log",
            currentProjectId: 1,
            show_create_log: false,
            show_search_log: false,
            currentPage: 1,
            currentPageSize: 10,
            fakeData: [
                {
                    lId: 1,
                    lContext:
                        "1建了一个超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级超级厉害的模型",
                    model: {
                        mId: 1,
                        mName: "模型A"
                    },
                    user: {
                        uId: 1,
                        uUsername: "asd"
                    },
                    project: {
                        pId: 1,
                        pName: "项目1"
                    },
                    lTime: new Date()
                }
            ],
            logs: [
                {
                    lId: null,
                    lContext: null,
                    model: {
                        mId: null,
                        mName: null
                    },
                    user: {
                        uId: null,
                        uUsername: null
                    },
                    project: {
                        pId: null,
                        pName: null
                    },
                    lTime: null
                }
            ],
            editForm:{lId: 0, lContext: ""},
            editFormVisible: false,
            currentUser: {
                mId: 1,
                mUsername: "hejizi"
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
            searchConditions: {
                timeRange: null,
                uUsername: null,
                mName: null
            }
        };
    },
    mounted: function() {
        this.$nextTick(function() {
            // Code that will run only after the
            // entire view has been rendered
            this.get_logs();
        });
    },
    methods: {
        get_logs() {
            var startPage = (this.currentPage - 1) * this.currentPageSize;
            //console.log(this.getLogsBypPidUrl+this.currentProjectId);

            //this.logs = this.fakeData.concat();
            //发送 get 请求
            var params= new Object();
            params.currentPage = this.currentPage;
            params.limit = this.currentPageSize;
            this.$http
                .get(this.getLogsBypPidUrl + "" + this.currentProjectId,{params:params})
                .then(
                    function(res) {
                        this.logs = res.body;
                    },
                    function(res) {
                        console.log(res.status);
                    }
                );
            return this.logs;
        },
        edit_log(log) {
            this.editContentFormVisible = true;
            this.editForm.lContext = log.lContext;
            this.editForm.lId = log.lId;
        },
        delete_log(log) {
            this.$confirm("此操作将永久删除该日志, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$http.delete(this.deleteLogUrl+"/"+log.lId, {timeout:3000 }).then(
                        function(res) {
                            if (res.body == true) {
                                this.$message({
                                    type: "success",
                                    message: "删除成功!"
                                });
                            } else {
                                this.$message({
                                    type: "error",
                                    message: "删除失败!"
                                });
                            }
                        },
                        function(res) {
                            this.$message({
                                type: "error",
                                message: "删除失败!"
                            });
                        }
                    );
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
        read_log_context(context) {
            const h = this.$createElement;
            this.$msgbox({
                title: "日志内容",
                message: h("p", null, [
                    h("span", { style: "color: black" }, context)
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
        submiteditForm() {
            var params = new Object();
            params.lContext = this.editForm.lContext;
            console.log(params);

            this.$http
                .put(this.updateLogUrl+'/'+this.editForm.lId,{}, {
                    params: params
                })
                .then(
                    function(res) {
                        this.$message({
                            type: "success",
                            message: "上传成功!"
                        });
                        this.get_logs();
                    },
                    function(res) {
                        this.$message({
                            type: "error",
                            message: "上传失败!"
                        });
                    }
                );
        },
        reseteditForm() {
            console.log(this.editForm.lId); 
            var findlId = this.editForm.lId;
            this.editForm.lContext = this.logs.find(function(log){ 
                return log.lId = findlId;
            }).lContext;
        },
        handleSizeChange(val) {
            this.currentPageSize = val;
            this.get_logs();
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.get_logs();
        },
        search_log() {
            //发送 get 请求

            var params = new Object();
            if (this.searchConditions.timeRange != null) {
                params.beginTime = this.searchConditions.timeRange[0].getTime();
                params.endTime = this.searchConditions.timeRange[1].getTime();
            }
            if (this.searchConditions.mName != null) {
                params.mName = this.searchConditions.mName;
            }
            if (this.searchConditions.uUsername != null) {
                params.uUsername = this.searchConditions.uUsername;
            }
            params.pId = this.currentProjectId;
            params.currentPage = this.currentPage;
            params.limit = this.currentPageSize;
            this.$http
                .get(this.searchLogsUrl, {
                    params: params
                })
                .then(
                    function(res) {
                        //console.log(res.body);
                        this.logs = res.body;
                    },
                    function(res) {
                        console.log(res.status);
                    }
                );
        }
    }
};
</script>
