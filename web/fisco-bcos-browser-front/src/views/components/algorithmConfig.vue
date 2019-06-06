<template>
    <div class="test-nav">
        <div class="algo-title">
            <p class="algo-title">
                节点部署：
            </p>
            <div class="input">
                <div class="card">
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <el-input :type="text" placeholder="节点数量" v-model="inputs.nodenum">
                                <template slot="prepend">节点数量：</template>
                            </el-input>
                        </el-col>
                        <el-col :span="11">
                            <el-input :type="text" placeholder="IP" v-model="inputs.ipadd">
                                <template slot="prepend">IP：</template>
                            </el-input>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <el-input :type="text" placeholder="起始rpc端口" v-model="inputs.startrpc">
                                <template slot="prepend">起始rpc端口：</template>
                            </el-input>
                        </el-col>
                        <el-col :span="11">
                            <el-input :type="text" placeholder="起始p2p端口" v-model="inputs.startp2p">
                                <template slot="prepend">起始p2p端口：</template>
                            </el-input>
                        </el-col>
                    </el-row>

                    <el-row :gutter="20" style="margin-left: 100px">
                        <el-col :span="4">
                            <div class="grid-content">
                                <label class="label">共识算法选择：</label>
                            </div>
                        </el-col>
                        <el-col :span="8">
                            <el-select v-model="inputs.algorithm" placeholder="请选择">
                                <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-col>
                        <el-button type="primary" @click="sub()" style="width: 120px">确认</el-button>
                    </el-row>
                </div>
            </div>
        </div>
        <h1>提交的信息： {{submsg}}</h1>
    </div>
</template>

<script>
    export default {
        name: "algorithmConfig",
        data (){
            return {
                inputs:{
                    nodenum:'',
                    ipadd:'',
                    startrpc:'',
                    startp2p:'',
                    algorithm:''
                },
                submsg:'',
                options: [{
                    value: 'raft',
                    label: 'raft算法'
                }, {
                    value: 'pbft',
                    label: 'pbft算法'
                }, {
                    value: 'tendermint',
                    label: 'tendermint算法'
                }],
            }
        },
        methods:{
            sub (){
                this.submsg = this.inputs;
            },
        }
    }
</script>

<style scoped>
    .algo-title{
        margin-left: 25px;
        color: white;
        font-size: 25px;
    }
    .test-nav{
        margin-left: 48px;
        width: 93%;
        padding: 5px 0;
        background-color: #3b3e54;
        color: #fff;
    }
    .card {
        padding: 10px;
    }
    .el-row {
        margin-top: 10px;
        margin-bottom: 20px;
    }
    .el-col {
        border-radius: 4px;
    }
    .input {
        margin-left: 70px;
        margin-right: 50px;
    }
    .grid-content {
        font-size: 20px;
    }
</style>
