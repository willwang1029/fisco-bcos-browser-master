<template>
    <div>
        <p class="time-title">
            测试时间：{{testTime}}
        </p>
        <h1 class="test-text">Summary</h1>
        <div class="home-center">
            <el-table :data="roundsData"  :header-cell-style="bgTable" :row-class-name="tableRowClassName" :cell-style="tableCellStyle"
                      v-loading="loading3" element-loading-text="数据加载中..."  element-loading-background="rgba(0, 0, 0, 0.8)">
                <el-table-column type="index" label="Test"  :show-overflow-tooltip="true" index="indexMethod" align="center"></el-table-column>
                <el-table-column  label="Name"  :show-overflow-tooltip="true" prop="name" align="center"></el-table-column>
                <el-table-column  label="Succ"  :show-overflow-tooltip="true" prop="success" align="center"></el-table-column>
                <el-table-column label="Fail"  :show-overflow-tooltip="true" prop="failed" align="center"></el-table-column>
                <el-table-column  label="Send Rate"  :show-overflow-tooltip="true" prop="sendRate" align="center" :formatter="sendFormatter"></el-table-column>
                <el-table-column  label="Max Latency"  :show-overflow-tooltip="true" prop="maxLatency" align="center" :formatter="maxFormatter"></el-table-column>
                <el-table-column  label="Min Latency"  :show-overflow-tooltip="true" prop="minLatency" align="center" :formatter="minFormatter"></el-table-column>
                <el-table-column  label="Throughput"  :show-overflow-tooltip="true" prop="throughput" align="center" :formatter="putFormatter"></el-table-column>
            </el-table>
        </div>



        <div class="home-center">
            <div v-for="(item,index) in roundsData">
                <div class="divtest"></div>
                <p class="big-title">
                    round {{index}} - {{item.name}}
                </p>
                <p class="mini-title">
                    performance metrics
                </p>

                <el-table :data="ObjectToArray(item)" :header-cell-style="bgTable" :row-class-name="tableRowClassName" :cell-style="tableCellStyle"
                          v-loading="loading3" element-loading-text="数据加载中..."  element-loading-background="rgba(0, 0, 0, 0.8)">
                    <el-table-column  label="Name"  :show-overflow-tooltip="true" prop="name" align="center"></el-table-column>
                    <el-table-column  label="Succ"  :show-overflow-tooltip="true" prop="success" align="center"></el-table-column>
                    <el-table-column label="Fail"  :show-overflow-tooltip="true" prop="failed" align="center"></el-table-column>
                    <el-table-column  label="Send Rate"  :show-overflow-tooltip="true" prop="sendRate" align="center" :formatter="sendFormatter"></el-table-column>
                    <el-table-column  label="Max Latency"  :show-overflow-tooltip="true" prop="maxLatency" align="center" :formatter="maxFormatter"></el-table-column>
                    <el-table-column  label="Min Latency"  :show-overflow-tooltip="true" prop="minLatency" align="center" :formatter="minFormatter"></el-table-column>
                    <el-table-column  label="Throughput"  :show-overflow-tooltip="true" prop="throughput" align="center" :formatter="putFormatter"></el-table-column>
                </el-table>



                <p class="mini-title">
                    resource consumption
                </p>
                <div class="table-body">
                    <el-table :data="item.resource"  :header-cell-style="bgTable" :row-class-name="tableRowClassName" :cell-style="tableCellStyle"
                              v-loading="loading3" element-loading-text="数据加载中..."  element-loading-background="rgba(0, 0, 0, 0.8)">
                        <el-table-column  label="TYPE"  :show-overflow-tooltip="true" prop="type" align="center"></el-table-column>
                        <el-table-column  label="NAME"  :show-overflow-tooltip="true" prop="name" align="center"></el-table-column>
                        <el-table-column  label="Memory(max)"  :show-overflow-tooltip="true" prop="maxMemory" align="center" :formatter="maxmeFormatter"></el-table-column>
                        <el-table-column  label="Memory(avg)"  :show-overflow-tooltip="true" prop="avgMemory" align="center" :formatter="minmeFormatter"></el-table-column>
                        <el-table-column  label="CPU(max)"  :show-overflow-tooltip="true" prop="maxCPU" align="center" :formatter="maxCPUFormatter"></el-table-column>
                        <el-table-column  label="CPU(avg)"  :show-overflow-tooltip="true" prop="avgCPU" align="center" :formatter="avgCPUFormatter"></el-table-column>
                        <el-table-column  label="Traffic In"  :show-overflow-tooltip="true" prop="trafficIn" align="center" :formatter="tinFormatter"></el-table-column>
                        <el-table-column  label="Traffic Out"  :show-overflow-tooltip="true" prop="trafficOut" align="center" :formatter="toutFormatter"></el-table-column>
                        <el-table-column  label="Disc Read"  :show-overflow-tooltip="true" prop="discRead" align="center" :formatter="drFormatter"></el-table-column>
                        <el-table-column  label="Disc Write"  :show-overflow-tooltip="true" prop="discWrite" align="center" :formatter="dwFormatter"></el-table-column>
                    </el-table>
                </div>
            </div>
        </div>
    </div>




</template>

<script>
    import {getJson} from "../../api/api";
    import date from "../../util/timechange";

    export default {
        name: "testDetail",
        data:function () {
            return{
                bgTable: {
                    backgroundColor: '#3b3e54',
                    color: 'white',
                    textAlign: 'center',
                    borderColor: '#666'
                },
                tableCellStyle: {
                    backgroundColor: '#3b3e54',
                    color: 'white',
                    width: '450px',
                    borderColor: '#666'
                },
                loading3: false,
                roundsData:"",
                testTime:""
            }
        },
        mounted: function(){
            this.Test("test");
        },
        methods:{
            Test:function (val) {
                let result="";
                getJson(result).then(res => {
                    console.log(res.data);
                    this.roundsData=res.data.data.rounds;
                    this.testTime=res.data.data.timestamp;
                    this.testTime = date(this.testTime,'yyyy-MM-dd HH:mm:ss')
                }).catch(err=>{
                    message(constant.ERROR,'error');
                })
            },
            indexMethod:function(index) {
                return index +1;
            },
            sendFormatter(row,cellValue){
                if(row.sendRate){
                    return row.sendRate+' tps';
                }
            },
            maxFormatter(row,cellValue){
                if(row.maxLatency){
                    return row.maxLatency+' s';
                }
            },
            minFormatter(row,cellValue){
                if(row.minLatency){
                    return row.minLatency+' s';
                }
            },
            putFormatter(row,cellValue){
                if(row.throughput){
                    return row.throughput+' tps';
                }
            },
            maxmeFormatter(row,cellValue){
                if(row.maxMemory){
                    var result=(row.maxMemory/1048576).toFixed(1);
                    return result+' MB';
                }
            },
            minmeFormatter(row,cellValue){
                if(row.avgMemory){
                    var result=(row.avgMemory/1048576).toFixed(1);
                    return result+' MB';
                }
            },
            maxCPUFormatter(row,cellValue){
                if(row.maxCPU){
                    return row.maxCPU*100+'%';
                }
            },
            avgCPUFormatter(row,cellValue){
                if(row.avgCPU){
                    return row.avgCPU*100+'%';
                }
            },
            tinFormatter(row,cellValue){
                return row.trafficIn+' B';
            },
            toutFormatter(row,cellValue){
                return row.trafficOut+' B';
            },
            drFormatter(row,cellValue){
                return row.discRead+' B';
            },
            dwFormatter(row,cellValue){
                return row.discWrite+' B';
            },
            ObjectToArray(object){
                var array = [];
                array[0] = object;
                return array;
            }
        },
    }
</script>

<style>
    .test-text{
        color: white;
        margin-left: 55px;
    }
    .time-title{
        margin-left: 55px;
        color: white;
        font-size: 15px;
    }
    .big-title{
        font-size: 25px;
        color: white;
        margin-left: 27px;
    }
    .mini-title{
        font-size: 20px;
        color: #82848a;
        margin-left: 27px;
    }
    .home-center{
        margin-top: 20px;
        margin-left: 30px;
        margin-right:30px;
    }
    .table-body{
        padding-bottom: 50px;
    }
    .divtest{
        width: 1465px;
        border-bottom: 1px solid #82848a;
    }
</style>
