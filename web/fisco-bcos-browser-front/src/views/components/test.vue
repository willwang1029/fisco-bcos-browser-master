<template>
    <div class="search-main" style="height: auto;">
        <div class="container">
            <p class="test-content">caliper测试报告</p>
            <div class="search-nav">
                <div class="hashInput">
                    <el-input placeholder="请输入测试轮数" v-model="searchKeyValue" class="input-with-select">
                        <el-button slot="append" icon="el-icon-search" @click="search" :disabled="submitDisabled"></el-button>
                    </el-input>
                </div>
            </div>
            <div class="search-table">
                <el-table :data="testList" element-loading-text="数据加载中..." element-loading-background="rgba(0, 0, 0, 0.8)">
<!--                    <el-table-column prop="testId" label="测试轮数" min-width="100px"  align="center" :show-overflow-tooltip="true"></el-table-column>-->
                    <el-table-column type="index" label="序号" width="300" align="center" min-width='300px'></el-table-column>
                    <el-table-column prop="testTime" label="测试时间" width="700" min-width="120px" :show-overflow-tooltip="true" align="center"></el-table-column>
                    <el-table-column label="查看测试报告" min-width="150px"align="center">
                        <template slot-scope="scope">
<!--                            <el-button type="primary" @click="linkPage('testDetail','testId',scope.$index)">查看</el-button>-->

                            <el-button type="primary" @click="goDetail(scope.$index)">查看</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
    </div>
</template>

<script>
    import {getTestList} from "@/api/api";
    import {goPage, MonthState, timeState} from "../../util/util";
    import date from "../../util/timechange";
    import testConfig from "./testConfig";
    import testDetail from "./testDetail";
    import {getTestResult} from "../../api/api";
    let minMonthData=null;
    let maxMonthData=null;
    let months=MonthState((new Date()).getTime())
    export default {
        name: "test",
        data:function () {
            return{
                searchKeyValue:"",
                groupId:null,
                testList:[],
                btitle:'caliper测试报告',
                pagination: {
                    currentPage: 1,
                    pageSize: 10,
                    total: 0,
                },
                submitDisabled:false,
                loading:false,
                blockNumber:null,
                setIntervalTime:null,
                chainType: this.$route.query.chainType || "01",
                ruleForm: {
                    userName: '', //用户名
                    password: ''  //密码
                },
                testResult:{}
            }
        },
        mounted: function(){
            this.getCookie();
            this.Test("test");

        },
        beforeDestroy: function () {
            window.clearInterval(this.setIntervalTime);
        },
        methods:{
            Test:function (val) {
                let result={
                    username:this.ruleForm.userName
                }
                getTestList(result).then(res => {
                    let timeresult = res.data.data
                    timeresult.forEach(item => {
                        item.testTime = date(item.testTime,'yyyy-MM-dd HH:mm:ss')
                    });
                    this.testList = timeresult;
                }).catch(err=>{
                    message(constant.ERROR,'error');
                })
            },
            getCookie:function(){
                if (document.cookie.length>0){
                    var arr=document.cookie.split(';');
                    for(var i=0;i<arr.length;i++){
                        var arr2=arr[i].split('=');
                        if(arr2[0]=='userName'){
                            this.ruleForm.userName=arr2[1];
                        }else if(arr2[0]=='userPwd'){
                            this.ruleForm.password=arr2[1];
                        }
                    }
                }
            },
            handleSizeChange(val) {
                this.pagination.pageSize = val;
                this.pagination.currentPage = 1;
                this.searchTbBlockInfo();
            },
            handleCurrentChange(val) {
                this.pagination.currentPage = val;
                this.searchTbBlockInfo();
            },
            linkPage: function (name,label,data) {
                return goPage(name,label,data);
            },
            goDetail: function (val) {
                // let result={
                //     userId:val
                // }
                // getTestResult(result).then(res => {
                //     let timeresult = res.data.data
                //     timeresult.forEach(item => {
                //         item.testTime = date(item.testTime,'yyyy-MM-dd HH:mm:ss')
                //     });
                //     this.testResult = timeresult;
                // }).catch(err=>{
                //     message(constant.ERROR,'error');
                // })
                let a=this.testList[val].testId;
                this.linkPage('testDetail','testId',a)
            }
        },
    }
</script>

<style scoped>
.test-content{
    width: 1000px;
    height: 20px;
    font-size: 16px;
    font-family: "PingFang SC";
    font-weight: 400;
    color: white;
    line-height:20px;
}
</style>
