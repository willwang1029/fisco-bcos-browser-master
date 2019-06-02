<template>
    <div class="login-container">
        <div class="cover-container">
            <div class="register-title">新用户注册</div>
            <el-form ref="loginForm" :model="registerForm" :rules="registerRules" class="register-form" label-position="left">

                <el-form-item prop="username">
                    <label>用户名：</label>
                    <el-input :type="text" v-model="registerForm.username" placeholder="请输入用户名" name="username" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item prop="email">
                    <label>邮箱：</label>
                    <el-input :type="text" v-model="registerForm.email" placeholder="请输入邮箱" name="email" style="margin-left: 12px" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <label>密码：</label>
                    <el-input :type="passwordType" v-model="registerForm.password" placeholder="请输入密码" style="margin-left: 12px" name="password"
                              auto-complete="on"></el-input>
                </el-form-item>

                <el-button :loading="loading" type="primary" style="width: 100%;margin-top: 20px;" @click="submituser('registerForm')">
                    注册
                </el-button>

                <el-row style="margin-top: 20px;margin-left: 50px">
                    <el-button type="text" style="margin-left: 80px" @click="linkPage('index',chainType)">去登陆</el-button>
                </el-row>
            </el-form>
            <div class="shadow-bottom shadow-1"></div>
            <div class="shadow-bottom shadow-2"></div>
        </div>
    </div>

</template>

<script>
    import {goPage} from "../../util/util";
    import {adduser} from "../../api/api";
    import constant from "../../util/constant";
    import errorcode from "@/util/errorCode"

    export default {
        name: "register",
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
            const validateEmail = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请正确填写邮箱'));
                } else {
                    if (value !== '') {
                        var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                        if(!reg.test(value)){
                            callback(new Error('请输入有效的邮箱'));
                        }
                    }
                    callback();
                }
            }
            return{
                loading:false,
                companyName:'fisco-bcos网站',
                registerForm:{
                    username:'',
                    email:'',
                    password:'',
                },
                registerRules:{
                    username: [{ required: true, trigger: 'blur', validator: validateUsername }],
                    password: [{ required: true, trigger: 'blur', validator: validatePassword }],
                    email:    [{ required:true,  trigger: 'blur', validator: validateEmail}]
                },
                passwordType:'password',
                showDialog:false,
                redirect:undefined,
                chainType: this.$route.query.chainType || "01",
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
            linkPage: function (name,label,data) {
                return goPage(name,label,data);
            },
            submituser: function (formName) {
                let data = {
                    userName: this.registerForm.username,
                    email: this.registerForm.email,
                    passWord: this.registerForm.password,
                }
                adduser(data).then(res => {
                    if(res.data.code === 0){
                        this.$emit('success');
                        this.handleClose();
                    }else{

                    }
                }).catch(err => {
                    message('用户名或邮箱已存在','error');
                })
                goPage('index',this.chainType)
            },
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
    .register-title{
        padding-top: 60px;
        font-size: 40px;
        font-weight: 500;
        color: rgb(24,144,255);
        line-height: 56px;
        text-align: center;
    }
    .register-form{
        position: relative;
        width:300px;
        max-width: 100%;
        padding: 50px 35px 0;
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
