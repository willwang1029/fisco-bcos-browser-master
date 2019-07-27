<template>
    <div class="test-nav">
        <div class="input">
<!--            <el-row :gutter="20">-->
<!--                <el-col :span="13">-->
<!--                    <div class="infotest">用户名：{{testResult.userName}}</div>-->
<!--                </el-col>-->
<!--                <el-col :span="7">-->
<!--                    <div class="infotest">邮箱：{{testResult.email}}</div>-->
<!--                </el-col>-->
<!--            </el-row>-->
<!--            <el-form class="form-block" status-icon :model="inputs" :rules="rules" ref="ruleForm" label-width="0px"-->
<!--                     :validate-on-rule-change="false">-->
<!--                <el-form-item>-->
<!--                    <el-input :type="passwordType" placeholder="原密码" v-model="inputs.oldpwd" name="password" auto-complete="on">-->
<!--                        <template slot="prepend">原密码：</template>-->
<!--                    </el-input>-->
<!--                </el-form-item>-->
<!--                <el-form-item>-->
<!--                    <el-input :type="passwordType" placeholder="新密码" v-model="inputs.newpwd1">-->
<!--                        <template slot="prepend">新密码：</template>-->
<!--                    </el-input>-->
<!--                </el-form-item>-->
<!--                <el-form-item>-->
<!--                    <el-input :type="passwordType" placeholder="新密码" v-model="inputs.newpwd2">-->
<!--                        <template slot="prepend">新密码：</template>-->
<!--                    </el-input>-->
<!--                </el-form-item>-->
<!--                <el-form-item>-->
<!--                    <el-button style="display:block;margin:5px auto;" type="primary" @click="changepwd()">修改密码</el-button>-->
<!--                </el-form-item>-->
<!--            </el-form>-->

            <el-form class="form-block" status-icon :model="inputs" :rules="rules" ref="ruleForm" label-width="0px"
                     :validate-on-rule-change="false">
                <el-row :gutter="20">
                    <el-col :span="13">
                        <el-form-item style="width: 400px">
                            <el-input :type="passwordType" placeholder="原密码" v-model="inputs.oldpwd" name="password" auto-complete="on">
                                <template slot="prepend">原密码：</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="7">
                        <div class="infotest">用户名：{{testResult.userName}}</div>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="13">
                        <el-form-item style="width: 400px">
                            <el-input :type="passwordType" placeholder="新密码" v-model="inputs.newpwd1">
                                <template slot="prepend">新密码：</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="7">
                        <div class="infotest">邮箱：{{testResult.email}}</div>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="13">
                        <el-form-item style="width: 400px">
                            <el-input :type="passwordType" placeholder="新密码" v-model="inputs.newpwd2">
                                <template slot="prepend">新密码：</template>
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="13">
                        <el-form-item style="width: 400px">
                            <el-button style="display:block;margin:5px auto;" type="primary" @click="changepwd()">修改密码</el-button>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
    </div>
</template>

<script>
    import { getUserInfo, modifyUser } from '../../api/api'
    import { message } from '@/util/util'
    import constant from '@/util/constant'
    import {goPage} from "../../util/util";

    export default {
        name: 'userInfo',
        data: function() {
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
                    userName: '', // 用户名
                    password: '' // 密码
                },
                passwordType: 'password',
                testResult: {},
                chainType: this.$route.query.chainType || "01",
            }
        },
        mounted: function() {
            this.getCookie()
            this.setInfo()
        },
        methods: {
            getCookie: function() {
                if (document.cookie.length > 0) {
                    var arr = document.cookie.split(';')
                    for (var i = 0; i < arr.length; i++) {
                        var arr2 = arr[i].split('=')
                        if (arr2[0] === 'userName') {
                            this.ruleForm.userName = arr2[1]
                        } else if (arr2[0] === 'userPwd') {
                            this.ruleForm.password = arr2[1]
                        }
                    }
                }
            },
            setInfo: function() {
                let result = {
                    userName: this.ruleForm.userName
                }
                getUserInfo(result)
                    .then(res => {
                        let inforesult = res.data.data
                        this.testResult = inforesult
                    })
                    .catch(err => {
                        message(constant.ERROR, 'error')
                    })
            },
            linkPage: function (name,label,data) {
                return goPage(name,label,data);
            },
            changepwd: function() {
                if (this.inputs.oldpwd.length < 6 || this.inputs.newpwd1.length < 6 || this.inputs.newpwd2.length < 6) {
                    message(constant.PSW_ERROR1, 'error')
                } else if (!this.inputs.newpwd1.replace(/\d/g, '') || !this.inputs.newpwd2.replace(/\d/g, '')) {
                    message(constant.PSW_ERROR2, 'error')
                } else if (this.inputs.newpwd1 !== this.inputs.newpwd2) {
                    message(constant.PSW_ERROR3, 'error')
                } else if (this.inputs.oldpwd !== this.testResult.passWord) {
                    message(constant.PSW_ERROR4, 'error')
                } else {
                    let result = {
                        userName: this.ruleForm.userName,
                        passWord: this.inputs.newpwd1
                    }
                    modifyUser(result)
                        .then(res => {
                            message(constant.PSW_SUCCESS, 'success')
                            this.inputs.oldpwd = ''
                            this.inputs.newpwd1 = ''
                            this.inputs.newpwd2 = ''
                            this.linkPage('index',this.chainType);
                        })
                        .catch(err => {
                            message(constant.ERROR, 'error')
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
        margin-top: 30px;
    }

    .el-row {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .el-col {
        border-radius: 4px;
    }

    .infotest {
        font-size: 20px;
        margin-bottom: 30px;
    }
    .form-block {
        margin-left: 100px;
    }
</style>
