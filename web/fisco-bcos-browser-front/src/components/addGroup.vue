<template>
    <div class="group-dialog">
        <el-dialog
            title="新增测试链"
            :visible.sync="groupVisible"
            width="600px"
            :before-close="handleClose">
            <div class="node-model-conetnt" style="padding-left: 0">
                <el-form :model="groupForm" :rules="rules" ref="groupForm" label-width="120px" class="demo-ruleForm">
                    <el-form-item label="测试链ID：" prop="id">
                        <el-input v-model="groupForm.id"  placeholder="请输入真实的测试链ID"></el-input>
                    </el-form-item>
                    <el-form-item label="测试链名称：" prop="name">
                        <el-input v-model="groupForm.name" placeholder="请输入测试链名称"></el-input>
                    </el-form-item>
                </el-form>
                <div class="group-label">
                    <span class="group-label-title">描述：</span>
                    <el-input  type="textarea"  v-model="groupInfo" class="group-label-input" placeholder="请输入描述"></el-input>
                </div> 
            </div>
            <div slot="footer" class="dialog-footer group-footer">
                <el-button type="primary" @click="submit('groupForm')">确 定</el-button>
            </div>
        </el-dialog> 
    </div>
</template>
<script>
import {addGroup} from "@/api/api"
import {message} from '@/util/util'
import constant from '@/util/constant'
import errorcode from "@/util/errorCode"

export default {
    name: 'addGroup',
    props: ['show'],
    data: function(){
        return {
            groupForm: {
                name: "",
                id: null
            },
            rules:{
                name: [
                    {required: true, message: '请输入测试链名称', trigger: 'blur'},
                    {min: 1,max:16, message: '长度不能超过16位', trigger: 'blur'},
                ],
                id: [
                    {required: true, message: '请输入测试链id', trigger: 'blur'},
                    {min: 1,max:16, message: '长度不能超过16位', trigger: 'blur'},
                    {pattern:/^[1-9]\d*$/,message: '格式不正确', trigger: 'blur'}
                ] 
            },
            groupInfo: "",
            groupVisible: this.show || false,
        }
    },
    methods: {
        handleClose: function(){
            this.$emit('close',false)
        },
        submit: function (formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$confirm('确认提交？',{center:true}).then(_ => {
                        this.addGroups();
                    }).catch(_ => {
                    });
                }else{
                    return false;
                }
            })
        },
        addGroups: function(){
            let data = {
                groupId: this.groupForm.id,
                groupName: this.groupForm.name,
                groupDesc: this.groupInfo
            }
            addGroup(data).then(res => {
                if(res.data.code === 0){
                    this.$emit('success');
                    this.handleClose();
                    message(constant.ADD_GROUP_SUCCESS,'success')
                }else{
                    message(errorcode[res.data.code].cn,'error')
                }
            }).catch(err => {
                message(constant.ERROR,'error');
            })
        },
    }
}
</script>
<style scoped>
.group-label{
    padding: 5px 0;
}
.group-label-title{
    display: inline-block;
    width: 120px;
    color: #fff;
    text-align: right;
    padding-right: 15px;
    vertical-align: top;
    box-sizing: border-box;
}
.group-label-input{
    width: 400px;
}
.group-dialog >>>.el-dialog__footer{
    background-color: #2a2c3b;
    text-align: center;
}
.group-dialog >>>.el-form-item__label{
    color: #fff
}
</style>


