<template>
    <div class="search-main">
        <div class="container">
            <v-nav :hrTitle="btitle" :hrcontent="btitle"></v-nav>
            <div class="search-nav">
                <div class="hashInput">
                    <el-button type="primary" @click="add"><i class="el-icon-plus"></i>新增测试链</el-button>
                    <el-button type="primary" @click="startchain()">启动</el-button>
                    <el-button type="primary" @click="stopchain()">暂停</el-button>
                </div>
            </div>
            <div class="search-table">
                <el-table :data="grouplist" v-loading="loading" element-loading-text="数据加载中..."
                          element-loading-background="rgba(0, 0, 0, 0.8)">
                    <el-table-column type="index" label="序号" align="center" min-width='60px'></el-table-column>
                    <el-table-column prop="groupId" label="测试链id" align="center"></el-table-column>
                    <el-table-column prop="groupName" label="测试链名称" align="center"></el-table-column>
                    <el-table-column prop="groupDesc" label="测试链描述" align="center"></el-table-column>
                    <el-table-column label="操作" width="100" align="center">
                        <template slot-scope="scope">
                            <i class="el-icon-delete" style="cursor:pointer" @click="deleteData(scope.row)"></i>
                        </template>
                    </el-table-column>
                    <el-table-column prop="status" label="状态" align="center" width="80">
                        <template slot-scope="scope">
                            <el-tag>{{scope.row.status}}</el-tag>
                        </template>
<!--                        <span class="block-label"></span>-->
                    </el-table-column>
                    <el-table-column min-width="200px" align="center">
                        <template slot-scope="scope">
                            <el-button type="primary" @click="linkPage('nodeConfig',chainType)">节点配置</el-button>
                            <el-button type="primary" @click="linkPage('contractConfig',chainType)">合约配置</el-button>
                            <el-button type="primary" @click="linkPage('testConfig',chainType)">测试配置</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <add-group @close="addGroups" v-if="addGroupShow" @success='addSuccess' :show='addGroupShow'></add-group>
    </div>
</template>
<script>
    import nav from '@/components/content-nav'
    import {getGroupList, deleteGroup, startShell, stopShell} from "@/api/api"
    import addGroup from "@/components/addGroup"
    import {message} from '@/util/util'
    import constant from '@/util/constant'
    import errorcode from "@/util/errorCode"
    import Bus from "@/bus"
    import {goPage} from "../../util/util";

    export default {
        name: "group",
        components: {
            'v-nav': nav,
            "add-group": addGroup
        },
        data: function () {
            return {
                btitle: "测试链配置",
                grouplist: [],
                addGroupShow: false,
                loading: false,
                chainType: this.$route.query.chainType || "01",
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                if (localStorage.getItem("groupList")) {
                    this.GetgroupList();
                } else {
                    this.add();
                }
            })
        },
        methods: {
            GetgroupList: function (val) {
                let data = {};
                getGroupList(data).then(res => {
                    if (res.data.code === 0) {
                        if (res.data.data.length) {
                            this.grouplist = res.data.data;
                            if(localStorage.getItem('status') === 'y'){
                                this.grouplist.forEach(item=>{
                                    item.status='已启动';
                                });
                            }else {
                                this.grouplist.forEach(item=>{
                                    item.status='未启动';
                                });
                            }

                            if (!localStorage.getItem("groupId")) {
                                this.groupId = res.data.data[0].groupId;
                                localStorage.setItem("groupId", this.groupId);
                            }
                            localStorage.setItem("groupList", JSON.stringify(res.data.data))
                            Bus.$emit('change', res.data.data)
                            if (val) {
                                this.$emit('addGroup');
                            }
                        } else {
                            this.addGroupShow = true;
                        }
                    } else {
                        message(errorcode[res.data.code].cn, 'error')
                    }
                }).catch(err => {
                    message(constant.ERROR, 'error');
                })
            },
            add: function () {
                this.addGroupShow = true;
            },
            addGroups: function () {
                this.addGroupShow = false;
                let add = "add"
                this.GetgroupList(add);
            },
            addSuccess: function () {
                this.addGroups();
            },
            deleteData: function (val) {
                this.$confirm('此操作不可恢复，请确认！', '删除测试链', {center: true}).then(_ => {
                    this.deleteItem(val);
                }).catch(_ => {
                });
            },
            deleteItem: function (val) {
                if (val.groupId == localStorage.getItem("groupId")) {
                    localStorage.setItem("groupId", "")
                }
                deleteGroup(val.groupId).then(res => {
                    if (res.data.code === 0) {
                        let item = 'delete'
                        this.GetgroupList(item);
                    } else {
                        message(errorcode[res.data.code].cn, 'error')
                    }
                }).catch(err => {
                    message(constant.ERROR, 'error');
                })
            },
            linkPage: function (name, label, data) {
                return goPage(name, label, data);
            },
            startchain: function () {
                let self = this;

                startShell().then(res=>{
                    setTimeout(() => {
                        self.grouplist.forEach((item)=>{
                            item.status = '已启动'
                        });
                        localStorage.setItem('status','y');
                        message(constant.START_SHELL, 'success');
                        self.GetgroupList();
                    }, 2000)
                }).catch(err => {
                    message(constant.ERROR,'error');
                })
            },
            stopchain: function (row) {
                let self = this;

                stopShell().then(res => {
                    setTimeout(() => {
                        self.grouplist.forEach((item)=>{
                            item.status = '未启动'
                        });
                        localStorage.setItem('status','n');
                        message(constant.STOP_SHELL, 'success');
                        self.GetgroupList();
                    }, 2000)

                }).catch(err => {
                    message(constant.ERROR, 'error');
                })
            }
        }
    }
</script>

