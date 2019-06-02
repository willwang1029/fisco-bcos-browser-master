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
                            <el-input placeholder="名称" v-model="item.val">
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
                            <el-button type="primary"><i class="el-icon-plus"></i>上传脚本</el-button>
                        </el-col>
                        <el-col :span="4">
                            <el-button type="primary" @click="removeConfig(index)"><i class="el-icon-delete"></i>删除本轮</el-button>
                        </el-col>
                    </el-row>
                </div>
            </div>
            <el-button style="margin-left: 370px;margin-top: 30px" type="primary" @click="addInput()"><i class="el-icon-plus"></i>新增轮数</el-button>
            <el-button style="margin-left: 370px;margin-top: 30px" type="primary" @click="sub()"><i class="el-icon-setting"></i> 开始测试</el-button>
            <h1>提交的信息： {{submsg}}</h1>
        </div>
    </div>
</template>

<script>
    export default {
        name: "testConfig",
        data (){
            return {
                inputs:[],
                submsg:'',
            }
        },
        methods: {
            addInput (){
                var obj = {};
                obj.id = this.inputs.length;
                obj.val = "";
                obj.time="";
                obj.speed="";
                this.inputs.push(obj);
            },
            sub (){
                this.submsg = this.inputs;
            },
            removeConfig(index){
                this.inputs.splice(index,1)
            }

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

</style>
