export default {
    //首页统计块列表常量
    TOTAL_STATISTICS_LIST: [
        {
            label: '当前块高',
            route: 'block',
            class: 'bg-8693f3 margin-right-15 margin-bottom-15',
            value: 0
        },
        {
            label: '交易总量',
            route: 'transaction',
            class: 'bg-bc8dee margin-left-15 margin-bottom-15',
            value: 0
        },
        {
            label: '正在处理的交易数',
            route: '',
            class: 'bg-ffa897 margin-top-15 margin-right-15',
            value: 0
        },
        {
            label: 'PBFT 当前视图',
            route: '',
            class: 'bg-89c3f8 margin-top-15  margin-left-15',
            value: 0
        }
    ],
    START_LIST:[
        {
            label: '测试链配置',
            route: 'groupConfig',
            description: '（测试链ID需要和区块链群组ID保持一致。）',
            class: 'bg-8693f3 margin-right-15 margin-bottom-15',
        },
        {
            label: '节点配置',
            route: 'nodeConfig',
            description: '（添加测试链所在的节点信息，用于区块链浏览器连接拉取相关展示信息。）',
            class: 'bg-bc8dee margin-left-15 margin-bottom-15',
        },
        {
            label: '合约配置',
            route: 'contractConfig',
            description: '（合约上传支持sol文件上传和将sol文件打包成zip包上传。）',
            class: 'bg-ffa897 margin-top-15 margin-right-15',
        }
    ],
    SEE_LIST:[
        {
            label: '实时信息概览',
            route: 'home',
            class: 'bg-8693f3 margin-right-15 margin-bottom-15',
        },
        {
            label: '查看区块',
            route: 'block',
            class: 'bg-bc8dee margin-left-15 margin-bottom-15',
        },
        {
            label: '查看交易',
            route: 'transaction',
            class: 'bg-ffa897 margin-top-15 margin-right-15',
        },
        {
            label: '查看测试报告',
            route: 'testDetail',
            class: 'bg-89c3f8 margin-top-15  margin-left-15',
        }
    ],
    HELP_LIST:[
        {
            label: '查看帮助',
            route: 'help',
            class: 'bg-8693f3 margin-right-15 margin-bottom-15',
        }
    ],
    MENU_LIST: [
        {
            title:'初始化环境',
            subMenu:[
                {
                    title: '节点部署',
                    name: 'algorithmConfig',
                },
                {
                    title: '测试链配置',
                    name: 'groupConfig',
                }
            ]
        },
        {
            title: '测试链管理',
            subMenu: [
                {
                    title: '节点配置',
                    name: 'nodeConfig',
                },
                {
                    title: '合约配置',
                    name: 'contractConfig',
                },
                
            ]
        },
        {
            title: '信息查看',
            subMenu: [
                {
                    title: '实时信息概览',
                    name: 'home',
                },
                {
                    title: '查看区块',
                    name: 'block',
                },
                {
                    title: '查看交易',
                    name: 'transaction',
                },
            ]
        },
        {
            title: '区块链测试',
            subMenu:[
                {
                    title: '性能测试配置',
                    name: 'testConfig',
                },
                {
                    title: '性能测试结果',
                    name: 'test',
                }
            ]
        }
    ],
//    error info
    ERROR: '服务器累了，请联系管理员',

    DELETE_NODE_SUCCESS: '删除节点成功',

    ADD_NODE_SUCCESS: '新增节点成功',

    EDIT_NODE_SUCCESS: '修改节点成功',

    DELETE_CHAIN_SUCCESS: '删除区块链成功',

    DELETE_PROJECT_SUCCESS: '删除项目成功',

    ADD_GROUP_SUCCESS: '新增测试链成功',

    ADD_USER_SUCCESS: '注册成功',

    PSW_ERROR1: '密码不得少于六位',

    PSW_ERROR2: '密码不得是纯数字',

    PSW_ERROR3: '两次输入的新密码不一样',

    PSW_ERROR4: '原密码不正确',

    PSW_SUCCESS: '修改成功',

    START_SHELL: '启动节点成功',

    STOP_SHELL: '暂停节点成功',

    INTERVALTIME: 20000,

    CHAIN_OPTION: [
        {
            label: '联盟委员链',
            value: "00"
        },
        {
            label: '业务存证链',
            value: "01"
        },
    ],
    LIVE: {
        "0": "否",
        "1": "是",
    },
    TRANSACTION_ITEM_DATA:  [
        {
            label: "blockHash",
            value: null
        },{
            label:"blockNumber",
            value: null
        },{
            label:"gas",
            value: null
        },{
            label:"from",
            value: null
        },{
            label:"transactionIndex",
            value: null
        },{
            label:"to",
            value: null
        },{
            label:"nonce",
            value: null
        },{
            label:"value",
            value: null
        },{
            label:"hash",
            value: null
        },{
            label:"gasPrice",
            value: null
        }
    ],
    RECEIPT_ITEM_DATA: [
        {
            label: "output",
            value: null
        },{
            label:"blockHash",
            value: null
        },{
            label:"gasUsed",
            value: null
        },{
            label:"blockNumber",
            value: null
        },{
            label:"contractAddress",
            value: null
        },{
            label:"from",
            value: null
        },{
            label:"transactionIndex",
            value: null
        },{
            label:"to",
            value: null
        },{
            label:"logsBloom",
            value: null
        },{
            label:"transactionHash",
            value: null
        },{
            label:"status",
            value: null
        }
    ],
    SYSTEM_CONTRACT_ADDRESS: [
        {
            contractName: 'Authority',
            contractAddress: '0x0000000000000000000000000000000000001005'
        },
        {
            contractName: 'CNS',
            contractAddress: '0x0000000000000000000000000000000000001004'
        },
        {
            contractName: 'SystemConfig',
            contractAddress: '0x0000000000000000000000000000000000001000'
        },
        {
            contractName: 'Consensus',
            contractAddress: '0x0000000000000000000000000000000000001003'
        }
    ]
}


