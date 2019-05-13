<template>
    <div>
        <div class="container">
            <div style="margin-left: 40px">
                <p class="in-text">区块链配置(初次登陆时须先配置)：</p>
            </div>
            <div class="index-head-data">
                <ul class="block-hover">
                    <li v-for="item in totalStatisticsList" :class="item.class" @click="linkPage(item.route,chainType)" :key="item.label" class="lg-width">
                        <span class="index-head-data-label">{{item.label}}</span>
                        <span class="index-head-data-content"> {{item.description}}</span>
                    </li>
                </ul>
            </div>
            <div style="margin-left: 40px">
                <p class="in-text">区块链信息：</p>
            </div>
            <div class="index-head-data">
                <ul class="block-hover">
                    <li v-for="item in totalList" :class="item.class" @click="linkPage(item.route,chainType)" :key="item.label" class="lg-width">
                        <span class="index-head-data-label2">{{item.label}}</span>
                    </li>
                </ul>
            </div>
            <div style="margin-left: 40px">
                <p class="in-text">查看帮助：</p>
            </div>
            <div class="index-head-data">
                <ul class="block-hover">
                    <li v-for="item in helpList" :class="item.class" @click="linkPage(item.route,chainType)" :key="item.label" class="lg-width">
                        <span class="index-head-data-label2">{{item.label}}</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
    import constant from "../../util/constant";
    import {goPage} from "../../util/util";

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
        }
    }
</script>

<style>
    .in-text{
        font-size: 20px;
        font-family: 黑体;
        color: white;
        line-height:20px;
    }
    .block-hover:hover{
        cursor: pointer;
    }
    .index-head-data{
        display: inline;
        width: calc(41% - 10px);
        box-sizing: border-box;
        vertical-align: middle;
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
    .index-head-data-label{
        display: block;
        color: white;
        font-size: 25px;
        text-align: center;
    }
    .index-head-data-label2{
        display: block;
        color: white;
        font-size: 25px;
        text-align: center;
        padding-top: 15px;
    }
    .index-head-data-content{
        height: 20px;
        display: block;
        color: white;
        font-size: 15px;
        text-align: center;
        font-family: 华文宋体;
    }
    /*.el-step__main {*/
    /*    .el-step__title.is-process {*/
    /*        color: #ffffff;*/
    /*    }*/
    /*    .el-step__description.is-process {*/
    /*        color: #ffffff;*/
    /*    }*/
    /*}*/
</style>
