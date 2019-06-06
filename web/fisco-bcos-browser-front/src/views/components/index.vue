<template>
    <div class="login-container">
        <div class="cover-container">
            <div class="login-title">平台登陆</div>
            <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" label-position="left">

                <el-form-item prop="username">
                    <label>用户名：</label>
                    <el-input :type="text" v-model="loginForm.username" placeholder="请输入用户名" name="username" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <label>密码：</label>
                    <el-input :type="passwordType" v-model="loginForm.password" placeholder="请输入密码" style="margin-left: 12px" name="password" auto-complete="on" @keyup.enter.native="handleLogin">

                    </el-input>
                </el-form-item>

                <el-button :loading="loading" type="primary" style="width: 100%;margin-top: 20px;" @click="handleLogin('loginForm')">
                    登陆
                </el-button>

                <el-row style="margin-top: 20px;margin-left: 50px">
<!--                    <el-button type="text">忘记密码</el-button>-->
                    <el-button type="text" style="margin-left: 80px" @click="linkPage('register',chainType)">注册账号</el-button>
                </el-row>
            </el-form>
            <div class="shadow-bottom shadow-1"></div>
            <div class="shadow-bottom shadow-2"></div>
        </div>
    </div>
</template>

<script>
    import {goPage} from "../../util/util";
    import {verityuser} from "../../api/api";

    export default {
        name: "index",
        data:function () {
            const validateUsername = (rule, value, callback) => {
                if (!isvalidUsername(value)) {
                    callback(new Error('请输入正确的账号'))
                } else {
                    callback()
                }
            }
            const validatePassword = (rule, value, callback) => {
                if (value.length < 6) {
                    callback(new Error('密码不能少于6位'))
                } else if (!value.replace(/\d/g, '')) {
                    callback(new Error('密码不能是纯数字'))
                } else {
                    callback()
                }
            }
            return{
                loading:false,
                companyName:'fisco-bcos网站',
                loginForm:{
                    username:'',
                    password:'',
                },
                loginRules:{
                    username: [{ required: true, trigger: 'blur', validator: validateUsername }],
                    password: [{ required: true, trigger: 'blur', validator: validatePassword }]
                },
                passwordType:'password',
                showDialog:false,
                redirect:undefined,
                chainType: this.$route.query.chainType || "01",
                ruleForm: {
                    userName: '', //用户名
                    password: ''  //密码
                },
            }
        },
        methods:{
            showPwd() {
                if (this.passwordType === 'password') {
                    this.passwordType = ''
                } else {
                    this.passwordType = 'password'
                }
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
            linkPage: function (name,label,data) {
                return goPage(name,label,data);
            },
            handleLogin: function (formName) {
                let data={
                    userName:this.loginForm.username,
                    passWord:this.loginForm.password,
                }
                verityuser(data).then(res => {
                    if(res.data.code === 0){
                        this.$message({
                            type: 'success',
                            message: '登陆成功!'
                        });
                        this.setCookie(data.userName,data.passWord,1);
                        goPage('home',this.chainType)
                    }
                    else {
                        this.$message({
                            type: 'error',
                            message: '用户名或密码错误!'
                        });
                    }
                }).catch(err => {
                    this.$message({
                        type: 'error',
                        message: '服务器错误!'
                    });
                })
            },
            setCookie(c_name,c_pwd,exdays){
                let exdate=new Date();
                exdate.setTime(exdate.getTime()+24*60*60*1000*exdays);
                window.document.cookie="userName"+"="+c_name+";path=/;expires="+exdate.toUTCString();
                window.document.cookie="userPwd"+"="+c_pwd+";path=/;expires="+exdate.toUTCString();
            }
        }
    }
</script>

<style scoped>
.login-container{
    min-height: 100%;
    width: 100%;
    background-color: #3b3e54;
    overflow: hidden;
    border: 0;
}
    .login-title{
        padding-top: 80px;
        font-size: 40px;
        font-weight: 500;
        color: rgb(24,144,255);
        line-height: 56px;
        text-align: center;
    }
    .login-form{
        position: relative;
        width:300px;
        max-width: 100%;
        padding: 60px 35px 0;
        margin: 0 auto;
        overflow: hidden;
    }
    .cover-container{
        margin: 100px auto 0;
        width:780px;
        height: 500px;
        background-color: #fff;
        border-radius: 10px;
        position: relative;
    }
    .el-form-item{
        background: rgba(255, 255, 255, 0.1);
        border-radius: 5px;
        color: #000;
        height: 42px;
    }
    .svg-container {
        padding: 6px 5px 6px 15px;
        color: #82848a;
        vertical-align: middle;
        width: 30px;
        display: inline-block;
    }
.el-input {
    display: inline-block;
    height: 47px;
    width: 75%;
}
.shadow-bottom {
    bottom: 0;
    position: absolute;
    border-radius: 10px;
}
.shadow-1 {
    height: 20px;
    width: 98%;
    left: 1%;
    box-shadow: 0 10px 0 rgba(255,255,255,0.8);
}
.shadow-2 {
    height: 30px;
    width: 96%;
    left: 2%;
    box-shadow: 0 20px 0 rgba(255,255,255,0.3);
}
</style>
