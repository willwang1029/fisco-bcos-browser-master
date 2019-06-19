<template>
    <div class="test-nav">
        <div class="input">
            <el-row :gutter="20">
                <el-col :span="13">
                    <div class="infotest">用户名：{{testResult.userName}}</div>
                </el-col>
                <el-col :span="7">
                    <div class="infotest">邮箱：{{testResult.email}}</div>
                </el-col>
            </el-row>
            <div style="text-align: center">
                <el-row :gutter="20">
                    <el-col :span="7">
                        <el-input :type="passwordType" placeholder="原密码" v-model="inputs.oldpwd" name="password"
                                  auto-complete="on">
                            <template slot="prepend">原密码：</template>
                        </el-input>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="7">
                        <el-input :type="passwordType" placeholder="新密码" v-model="inputs.newpwd1">
                            <template slot="prepend">新密码：</template>
                        </el-input>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="7">
                        <el-input :type="passwordType" placeholder="新密码" v-model="inputs.newpwd2">
                            <template slot="prepend">新密码：</template>
                        </el-input>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="4">
                        <el-button style="margin-left: 30px" type="primary" @click="changepwd()">修改密码</el-button>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
</template>

<script>
    import {getUserInfo, modifyUser} from "../../api/api";
    import {message} from '@/util/util';
    import constant from '@/util/constant';

    export default {
        name: "userInfo",
        data: function () {
            return {
                inputs: {
                    oldpwd: '',
                    newpwd1: '',
                    newpwd2: ''
                },
                userinfo: {
                    username: '',
                    email: '',
                    password: ''
                },
                ruleForm: {
                    userName: '', //用户名
                    password: ''  //密码
                },
                passwordType: 'password',
                testResult: {},
            }
        },
        mounted: function () {
            this.getCookie();
            this.setInfo();
        },
        methods: {
            getCookie: function () {
                if (document.cookie.length > 0) {
                    var arr = document.cookie.split(';');
                    for (var i = 0; i < arr.length; i++) {
                        var arr2 = arr[i].split('=');
                        if (arr2[0] == 'userName') {
                            this.ruleForm.userName = arr2[1];
                        } else if (arr2[0] == 'userPwd') {
                            this.ruleForm.password = arr2[1];
                        }
                    }
                }
            },
            setInfo: function () {
                let result = {
                    userName: this.ruleForm.userName
                }
                getUserInfo(result).then(res => {
                    let inforesult = res.data.data
                    this.testResult = inforesult
                }).catch(err => {
                    message(constant.ERROR, 'error');
                })
            },
            changepwd: function () {
                if (this.inputs.oldpwd.length < 6 || this.inputs.newpwd1.length < 6 || this.inputs.newpwd2.length < 6) {
                    message(constant.PSW_ERROR1, 'error');
                } else if (!this.inputs.newpwd1.replace(/\d/g, '') || !this.inputs.newpwd2.replace(/\d/g, '')) {
                    message(constant.PSW_ERROR2, 'error');
                } else if (this.inputs.newpwd1 != this.inputs.newpwd2) {
                    message(constant.PSW_ERROR3, 'error');
                } else if (this.inputs.oldpwd != this.testResult.passWord) {
                    message(constant.PSW_ERROR4, 'error');
                } else {
                    let result = {
                        userName: this.ruleForm.userName,
                        passWord: this.inputs.newpwd1
                    }
                    modifyUser(result).then(res => {
                        message(constant.PSW_SUCCESS, 'success');
                        this.inputs.oldpwd = '';
                        this.inputs.newpwd1 = '';
                        this.inputs.newpwd2 = '';
                    }).catch(err => {
                        message(constant.ERROR, 'error');
                    })
                }
            }
        }
    }
</script>

<style scoped>
    .test-nav {
        margin-left: 48px;
        width: 93%;
        padding: 5px 0;
        background-color: #3b3e54;
        color: #fff;
    }

    .input {
        margin-left: 53px;
        margin-right: 50px;
    }

    .el-row {
        margin-top: 20px;
        margin-bottom: 30px;
    }

    .el-col {
        border-radius: 4px;
    }

    .infotest {
        margin-top: 20px;
        font-size: 20px;
        margin-bottom: 30px;
    }
</style>
