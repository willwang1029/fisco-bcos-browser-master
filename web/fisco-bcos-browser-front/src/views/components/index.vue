<template>

    <div>
        <div class="index-button">
            <el-row>
                <el-button type="primary" @click="startshell">启动FISCO BCOS节点</el-button>
                <el-button type="primary" @click="stopshell">暂停FISCO BCOS节点</el-button>
            </el-row>
        </div>
    </div>
</template>

<script>
    import constant from "../../util/constant";
    import {goPage} from "../../util/util";
    import {startShell, stopShell} from "../../api/api";

    export default {
        name: "index",
        data() {
            return {
                active: 0,
                totalStatisticsList: constant.START_LIST,
                totalList:constant.SEE_LIST,
                helpList:constant.HELP_LIST,
                chainType: this.$route.query.chainType || "01",
            };
        },
        methods: {
            next() {
                if (this.active++ > 2) this.active = 0;
            },
            linkPage: function (name,label,data) {
                return goPage(name,label,data);
            },
            startshell:function () {
                let result="";
                startShell(result);
            },
            stopshell:function () {
                let result="";
                stopShell(result);
            }
        }
    }
</script>

<style>
    .index-button{
        margin-left: 110px;
        margin-top: 10px;
    }
    .index-head-data ul{

    }
    .index-head-data ul li{
        display: inline-block;
        height: 110px;
        padding: 20px 10px;
        font-size: 14px;
        color: #fff;
        box-sizing: border-box;
        width: 300px;
        margin: 0px 20px;
        vertical-align: top;
    }
</style>
