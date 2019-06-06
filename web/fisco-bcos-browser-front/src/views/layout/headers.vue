<template>
    <div>
        <div class="nav-header">
            <div class="container">
<!--                <div class="logo">-->
<!--                    <img style="width: 170px;" @click='link' src="../../assets/images/fisco-bcos-logo.png" class="image">-->
<!--                </div>-->
                <div class="nav-menu" style="float: left;">
                    <div class="nav-menu-item nav-item" style="display: inline-block;width: 160px;text-align:right">
                        <span>{{groupName}}</span>
                        <div class="nav-chainType"></div>
                        <ul class="select1">
                            <li v-for="item in groupList" style="cursor: default;" :key="item.groupId">
                                <span class="name" @click="checkGroup(item)">{{item.groupName}}</span>
                            </li>
                        </ul>
                        <i v-if="groupList.length" class="el-icon-caret-bottom icon-up icon"></i>
                        <i v-if="groupList.length" class="el-icon-caret-top icon-down icon"></i>
                    </div>
                </div>
                <div class="nav-menu" style="font-size: 20px;margin-left: 20px">

                    <el-dropdown>
                        <span class="el-dropdown-link" style="font-size: 20px;font-family: 'Arial Unicode MS';color: #f0f2f5">
                             {{ruleForm.userName}}<i class="el-icon-arrow-down el-icon--right"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item @click.native="linkPage('test',chainType)">我的测试</el-dropdown-item>
                            <el-dropdown-item @click.native="clearCookie">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>

                </div>
                <div class="nav-menu">
                    <div class="nav-menu-item nav-item" v-for="item in menu" :key='item.title'>{{item.title}}
                        <i class="el-icon-caret-bottom icon-up"></i>
                        <i class="el-icon-caret-top icon-down"></i>
                        <ul class="nav-menu-item-option">
                            <li class="option" v-for="list in item.subMenu" :key="list.name" @click="routerLink(list.name)">{{list.title}}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script type="es6">
import url from "@/api/url";
import router from "@/router";
import constant from "@/util/constant";
import { message,goPage } from "@/util/util";
import Bus from "@/bus"
import {startShell, stopShell} from "../../api/api";

export default {
    name: "headers",
    data: function() {
        return {
            menu: constant.MENU_LIST,
            groupList: [],
            groupName: "",
            chainNone: false,
            groupId: "",
            active: 0,
            chainType: this.$route.query.chainType || "01",
            ruleForm: {
                userName: '', //用户名
                password: ''  //密码
            },
        };
    },
    mounted: function () {
        this.$nextTick(function () {
            this.getGroupData();
            this.getCookie();
        })
        Bus.$on("change",data => {
            this.getGroupData();
            this.getCookie();
        })
    },
    methods: {
        // get groups
        getGroupData: function() {
            this.groupList = JSON.parse(localStorage.getItem("groupList"));
            if (this.groupList.length) {
                this.groupId = JSON.parse(localStorage.getItem("groupId"));
                this.groupList.forEach(value => {
                    if (value.groupId == this.groupId) {
                        this.groupName = value.groupName;
                    }
                });
            }
        },

        //Switching group
        checkGroup: function(val) {
            this.groupName = val.groupName;
            this.groupId = val.groupId;
            localStorage.setItem("groupId", this.groupId);
            router.push({
                name: "blankPage",
                query: {
                    path: this.$route.path
                }
            });
        },
        routerLink: function(name) {
            router.push({
                name: name
            });
        },
        link: function() {
            router.push({
                name: 'home'
            });
        },
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
        clearCookie:function () {
            this.setCookie("","",-1);//修改2值都为空，天数为负1天就好了
            this.linkPage('index',this.chainType)
        },
        setCookie(c_name,c_pwd,exdays){
            let exdate=new Date();
            exdate.setTime(exdate.getTime()+24*60*60*1000*exdays);
            window.document.cookie="userName"+"="+c_name+";path=/;expires="+exdate.toUTCString();
            window.document.cookie="userPwd"+"="+c_pwd+";path=/;expires="+exdate.toUTCString();
        }
    }
};
</script>
<style>
.nav-header {
    width: 100%;
    height: 80px;
    line-height: 80px;
    background-image: url("../../assets/images/header-ng.png");
    background-size: 100%;
    background-repeat: no-repeat;
    font-size: 0;
    color: #fff;
    text-align: center;
}
.nav-header .logo {
    display: inline-block;
    float: left;
    cursor: pointer;
}
.nav-header .image {
    display: inline;
    padding-top: 20px;
    cursor: pointer;
}
.nav-chainType {
    position: absolute;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
}
.nav-menu {
    float: right;
}
.nav-item {
    display: inline-block;
    font-size: 14px;
    width: 120px;
    cursor: pointer;
}
/*.nav-item:hover{*/
/*border-bottom: 2px solid red;*/
/*}*/
.nav-item .icon-up {
    display: inline !important;
}
.nav-item .icon-down {
    display: none !important;
}
.nav-item:hover .icon-up {
    display: none !important;
}
.nav-item:hover .icon-down {
    display: inline !important;
}
.nav-menu-item {
    position: relative;
}
.nav-menu-item-option {
    display: none;
    position: absolute;
    width: 100%;
    top: 80px;
    left: 0;
    padding: 10px 0;
    font-size: 14px;
    color: #333;
    background-color: #fff;
    cursor: pointer;
    line-height: 32px;
    z-index: 9999999;
    box-sizing: border-box;
    margin: 0;
    list-style: none;
}
.nav-menu-item:hover .nav-menu-item-option {
    display: block;
}
.nav-menu-item-option option {
    cursor: pointer;
    height: 32px;
}
.nav-menu-item-option li:hover {
    color: #3498db !important;
}
.nav-item input {
    display: inline-block;
    width: 120px;
    padding-right: 10px;
    background-color: rgba(0, 0, 0, 0);
    color: #fff;
    text-align: center;
    border: none;
    outline: none;
    font-size: 14px;
}
.nav-item .select {
    position: absolute;
    display: none;
    top: 100%;
    left: 0;
    width: 100%;
    margin: 0;
    background-color: #fff;
    /*padding: 20px 0;*/
    z-index: 999999;
    padding: 10px 0;
    font-size: 14px;
    list-style: none;
    text-align: center;
}
.nav-item .select1 {
    position: absolute;
    display: none;
    top: 100%;
    left: 50%;
    width: 100%;
    margin: 0;
    background-color: #fff;
    /*padding: 20px 0;*/
    z-index: 999999;
    padding: 10px 0;
    font-size: 14px;
    list-style: none;
    text-align: center;
}
.nav-item .select li {
    height: 32px;
    line-height: 32px;
    background-color: #fff;
    /*padding: 20px 0;*/
    font-size: 14px;
    color: #333;
}
.nav-item .select1 li {
    height: 32px;
    line-height: 32px;
    background-color: #fff;
    /*padding: 20px 0;*/
    font-size: 0;
    color: #333;
}

.nav-item .select li:hover {
    color: #3498db !important;
}

.nav-menu-item:hover .select {
    display: block;
}
.nav-item:hover .select1 {
    display: block;
}
.nav-item .name:hover {
    color: #3498db !important;
}
.nav-item .delete:hover {
    color: #3498db !important;
}
.nav-item .name {
    display: inline-block;
    width: 50%;
    cursor: pointer;
    font-size: 14px;
}
.nav-item .delete {
    display: inline-block;
    width: 50%;
    cursor: pointer;
    font-size: 14px;
}
.nav-item .add {
    font-size: 14px !important;
    cursor: pointer;
}
.nav-item .add:hover {
    color: #3498db !important;
}

.icon {
    position: absolute;
    left: 101%;
    top: 42%;
}

.header-button1{
    margin-right: 50px;
}
.header-button2{
    margin-right: 20px;
}
</style>
