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
                <el-table :data="testList"  element-loading-text="数据加载中..." element-loading-background="rgba(0, 0, 0, 0.8)">
                    <el-table-column prop="testId" label="测试轮数" min-width="120px" align="center" :show-overflow-tooltip="true"></el-table-column>
                    <el-table-column prop="testTime" label="测试时间" min-width="100px" :show-overflow-tooltip="true" align="center"></el-table-column>
                </el-table>
<!--                <div class="search-pagation">-->
<!--                    <div style="line-height: 40px;">-->
<!--                        <span>查询结果 : </span>-->
<!--                        <span>共计{{pagination.total}}条数据</span>-->
<!--                    </div>-->
<!--                    <el-pagination style="display: inline-block"-->
<!--                                   layout="sizes,prev, pager, next"-->
<!--                                   :total="pagination.total"-->
<!--                                   @size-change="handleSizeChange"-->
<!--                                   @current-change="handleCurrentChange"-->
<!--                                   :current-page.sync="pagination.currentPage"-->
<!--                                   :page-sizes="[10, 20, 30, 50]"-->
<!--                                   :page-size="pagination.pageSize">-->
<!--                    </el-pagination>-->
<!--                </div>-->
            </div>
        </div>
    </div>
</template>

<script>
    import {getTestList} from "@/api/api";
    import {MonthState, timeState} from "../../util/util";
    import date from "../../util/timechange";
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
            }
        },
        mounted: function(){
            this.Test("test");
        },
        beforeDestroy: function () {
            window.clearInterval(this.setIntervalTime);
        },
        methods:{
            Test:function (val) {
                let result="";
                getTestList(result).then(res => {
                    debugger
                    console.log(res.data.data);
                    let timeresult = res.data.data
                    timeresult.forEach(item => {
                        item.testTime = date(item.testTime,'yyyy-MM-dd HH:mm:ss')
                    });
                    this.testList = timeresult;
                }).catch(err=>{
                    message(constant.ERROR,'error');
                })
            },
            handleSizeChange(val) {
                this.pagination.pageSize = val;
                this.pagination.currentPage = 1;
                this.searchTbBlockInfo();
            },
            handleCurrentChange(val) {
                this.pagination.currentPage = val;
                this.searchTbBlockInfo();
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
