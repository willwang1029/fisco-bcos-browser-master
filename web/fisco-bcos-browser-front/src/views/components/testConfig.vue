<template>
    <div class="testcontain">
        <div class="test-nav">
            <p class="test1">性能测试配置</p>
            <div class="input">
                <div class="card" v-for="(item,index) in inputs" :key="index">
                    <el-row :gutter="20">
                        <el-col :span="3">
                            <div class="grid-content bg-purple">
                                <label class="label">第{{index+1}}轮：</label>
                            </div>
                        </el-col>
                        <el-col :span="10">
                            <el-input placeholder="名称" v-model="item.name">
                                <template slot="prepend">名称：</template>
                            </el-input>
                        </el-col>
                        <el-col :span="10">
                            <el-input placeholder="持续时间" v-model="item.time">
                                <template slot="prepend">持续时间：</template>
                            </el-input>
                        </el-col>
                    </el-row>
                    <el-row gutter="20">
                        <el-col :span="10" style="margin-left: 163px">
                            <el-input placeholder="发送速率" v-model="item.speed">
                                <template slot="prepend">发送速率：</template>
                            </el-input>
                        </el-col>
                        <el-col :span="4" style="margin-left: 80px">
                            <div class="hashInput">
                                <el-button type="primary"><i class="el-icon-plus"></i>上传脚本</el-button>
                                <input type="file" ref='file' id="file" class="inputFiles" multiple="multiple" accept=".json"  name="chaincodes" @change="upLoadJson(index,$event)"/>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <el-button type="primary" @click="removeConfig(index)"><i class="el-icon-delete"></i>删除本轮</el-button>
                        </el-col>
                    </el-row>
                </div>
            </div>
            <el-button style="margin-left: 370px;margin-top: 30px" type="primary" @click="addInput()"><i class="el-icon-plus"></i>新增轮数</el-button>
            <el-button style="margin-left: 370px;margin-top: 30px;margin-bottom: 20px" type="primary" @click="sub()"><i class="el-icon-setting"></i> 开始测试</el-button>
        </div>
    </div>
</template>

<script>
    import {config2, startTest} from "../../api/api";
    import { message } from '@/util/util'
    import constant from "../../util/constant";

    let Base64 = require("js-base64").Base64;
    export default {
        name: "testConfig",
        data (){
            return {
                inputs:[],
                scriptList:[],
                sublist:'',
                chainType: this.$route.query.chainType || "01",
                groupId: null,
            }
        },
        methods: {
            addInput (){
                var obj = {};
                obj.id = this.inputs.length;
                obj.name = "";
                obj.time="";
                obj.speed="";
                this.inputs.push(obj);
            },
            sub (){
                let data = {
                }
                if(this.scriptList.length){
                    data.data = [];
                    this.scriptList.forEach((value,index) => {
                        data.data[index] = {};
                        data.data[index].scriptPath = value.filepath;
                    })
                    this.inputs.forEach((value,index)=>{
                        data.data[index].name=value.name;
                        data.data[index].time=value.time;
                        data.data[index].speed=value.speed;
                    })
                }
                this.sublist=data.data
                config2(data).then(res => {
                    message('测试配置成功！','success')
                }).catch(err => {
                    message('测试配置失败', 'error')
                })
                this.groupId = localStorage.getItem("groupId")
                let result = {groupId: this.groupId}
                startTest(result).then(res => {
                    message('测试配置成功！','success')
                }).catch(err => {
                    message('测试配置失败', 'error')
                })
            },
            removeConfig(index){
                this.inputs.splice(index,1)
            },
            upLoadJson:function (index,e) {
                if(e.target.files.length == 1){
                    let num =0;
                    if(num == 0){
                        this.upLoad(index,e)
                    }
                }
            },
            upLoad: function(index,e) {
                let files = [];
                let filessize = [];
                let filetype = [];
                for(let i = 0; i < e.target.files.length; i++){
                    files[i] = e.target.files[i];
                    filessize[i] = Math.ceil(files[i].size / 1024);
                    filetype[i] = files[i].name.split(".")[1];
                    if (filessize[i] > 400) {
                        this.$message({
                            message: '文件大小超过400k，请上传小于400k的文件',
                            type: "error"
                        });
                    } else if (filetype[i] !== "json") {
                        this.$message({
                            message: '请上传.json格式的文件',
                            type: "error"
                        });
                    } else {
                        let filsObj = {};
                        filsObj.filename = files[i].name.split(".")[0];
                        filsObj.file = files[i].name;
                        filsObj.filepath='benchmark/fisco-bcos/v2.0/helloworld/'+files[i].name;
                        let reader = new FileReader(); //add a FileReader
                        reader.readAsText(files[i], "UTF-8"); //read file
                        let that=this;
                        that.scriptList.push(filsObj)
                        // reader.onload = function(evt) {
                        //     //that.inputs[index].json = evt.target.result; // read file content'
                        //     filsObj.fileString = Base64.encode(evt.target.result); // read file content
                        //     if(filsObj.fileString){
                        //         that.scriptList.push(filsObj)
                        //     }
                        // };
                    }
                }
                this.$refs.file.value = "";
            },
        }
    }
</script>

<style scoped>
    .test1{
        font-size: 27px;
        color: #f0f2f5;
        margin-left: 70px;
    }
    .test-nav{
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
    .card {
        padding: 10px;
        border: 1px solid #82848a;
    }
    .label {
        line-height: 36px;
        margin-left: 50px;
    }
    .el-row {
        margin-top: 10px;
        margin-bottom: 20px;
    }
    .el-col {
        border-radius: 4px;
    }
    .bg-purple {
        background: #8c939d;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
    .hashInput>.label{
        display: inline-block;
        width: 80px;
    }
    .inputFiles{
        position: absolute;
        opacity: 0;
        left: 804px;
        top: 0;
        width: 112px;
        height: 40px;

    }
</style>
