import url from './url'
import axios from 'axios'
import { post, get, patch, put, deleted } from './http'
import { reviseParam } from '@/util/util'

//get blockChain overview
export function getTbBlcokChainInfo(data) {
    return get({
        url: `${url.GET_TBBLOCK_CHAIN_INFO}/${data}`,
        method: 'get'
    })
}
//The amount of transaction information to obtain the current group for a certain period of time
export function getTxnByLastFourteenDay(data,list) {
    const params = reviseParam(data, list);
    return get({
        url: `${url.GET_TXNBY_LASTFOURTEENDAY}/${params.str}`,
        method: 'get',
        params: params.querys
    })
}


export function getTbBlockInfo(data,list) {
    const params = reviseParam(data, list);
    return get({
        url: `${url.GET_TBBLOCK_INFO}/${params.str}`,
        method: 'get',
        params: params.querys
    })
}

//Get a list of transaction information under the current group
export function getTbTransactionInfo(data,list) {
    const params = reviseParam(data, list);
    return get({
        url: `${url.GET_TBTRASACTION_INFO}/${params.str}`,
        method: 'get',
        params: params.querys
    })
}

//get a lots of nodes
export function getTbNodeConnection(data,list) {
    const params = reviseParam(data, list);
    return get({
        url: `${url.GET_TBNODE_COMNECTION}/${params.str}`,
        method: 'get',
        params: params.querys
    })
}

//Obtain transaction information based on transaction hash
export function getTbTransactionByPkHash(data,list) {
    const params = reviseParam(data, list);
    return get({
        url: `${url.GET_TBTRASACTION_BY_PKHASH}/${params.str}`,
        method: 'get',
    })
}
//Obtain transaction receipt information based on transaction hash
export function getTbTransactionReceiptByPkHash(data,list) {
    const params = reviseParam(data, list);
    return get({
        url: `${url.GET_TBTRASACTION__RECEIPT_BYPKHASH}/${params.str}`,
        method: 'get',
    })
}
// add a node
export function addNodeConfigRow(data) {
    return post({
        url: url.ADD_NODECONFIG_ROW,
        method: 'post',
        data: data
    })
}
//edit a node
export function editNodeConfigRow(data) {
    return put({
        url: url.EDIT_NODECONFIG_ROW,
        method: 'put',
        data: data
    })
}
//delete node
export function deleteNodeConfigRow(data,list) {
    const params = reviseParam(data, list);
    return deleted({
        url: `${url.DELETE_NODECONFIG_ROW}/${params.str}`,
        method: 'delete',
        // data: data
    })
}
export function getTbBlockByBlockHash(data,list) {
    const params = reviseParam(data, list);
    return get({
        url: `${url.GET_TBBLOCK_BYBLOCKHASH}/${params.str}`,
        method: 'get',
    })
}

export function addContract(data) {  // add new contract
    return post({
        url: url.ADD_CONTRACT,
        method: 'post',
        data: data
    })
}

export function getContractList(data,list) {  //get a list of contracts
    const params = reviseParam(data, list);
    return get({
        url: `${url.GET_CONTRACT_LIST}/${params.str}`,
        method: 'get',
    })  
}

export function deleteContract(data) {  //delete a contract
    return deleted({
        url: `${url.DELETE_CONTRACT}`,
        method: 'delete',
        params: data
    })
}

export function updateContract(data) {  //update a contract
    return put({
        url: url.UPDATE_CONTRACT,
        method: 'put',
        data: data
    })
}

// add a group
export function addGroup(data) {
    return post({
        url: url.ADD_GROUP,
        method: 'post',
        data: data
    })
}
//get groups
export function getGroupList(data) {
    return get({
        url: `${url.GET_GROUP_LIST}`,
        method: 'get'
    })
}
//delete a group
export function deleteGroup(data) {
    return deleted({
        url: `${url.DELETE_GROUP}/${data}`,
        method: 'delete'
    })
}
//Get contract binary code
export function getBytecode(data) {
    return get({
        url: url.GET_BYTECODE,
        method: 'post',
        data: data
    })
}

//Get the data needed for transaction parsing based on the transaction hash call
export function getAnalyzeData(data) {
    return get({
        url: url.GET_TRANSACTION_ANALYZEDATA,
        method: 'post',
        data: data
    })
}

//upload zip file
export function uploadData(data){
    return axios.post(url.UPLOAD,data,{
        headers: {
            "Content-Type": "multipart/form-data"
        }
    })
}
//add abi function 
export function addAbiFunction(data){
    return post({
        url: url.ADDFUNCTION,
        method: 'post',
        data: data
    })
}
//get abi function 
export function getAbiFunction(data){
    return get({
        url: `${url.GET_ABI_FUNCTION}/${data}`,
        method: 'get',
    })
}

//getAbi
export function getAbi(data){
    return post({
        url: `${url.GET_ABI}`,
        method: 'post',
        data: data
    })
}

//getTestList
export function getTestList(data){
    return get({
        url: `${url.GET_TEST_LIST}`,
        method: 'get',
        params: data
    })
}

export function getJson(data) {
    return get({
        url: `${url.GET_JSON}`,
        method: 'get',
        data: data
    })
}

export function startShell(data) {
    return get({
        url: `${url.START_SHELL}`,
        method: 'get',
        data: data
    })
}

export function stopShell(data) {
    return get({
        url: `${url.STOP_SHELL}`,
        method: 'get',
        data: data
    })
}

export function adduser(data){
    return post({
        url: `${url.ADD_USER}`,
        method: 'post',
        data: data
    })
}

export function verityuser(data) {
    return post({
        url: `${url.VERITY_USER}`,
        method: 'post',
        data: data
    })
}

export function getTestResult(data) {
    return get({
        url: `${url.GET_TEST_RESULT}`,
        method: 'get',
        params: data
    })
}

export function getUserInfo(data) {
    return get({
        url: `${url.GET_USER_INFO}`,
        method: 'get',
        params: data
    })
}

export function modifyUser(data) {
    return get({
        url: url.UPDATE_USER,
        method: 'get',
        params: data
    })
}

export function config2(data) {
    return post({
        url: url.ADD_CONFIG2,
        method: 'post',
        data: data
    })
}

export function startTest(data) {
    return post({
        url: url.START_TEST,
        method: 'post',
        data: data
    })
}

export function jsonSave(data) {
    return get({
        url: url.JSON_SAVE,
        method: 'get',
        params: data,
        timeout: 600000
    })
}

export function latestTest(data) {
    return get({
        url: url.LATEST_TEST,
        method: 'get',
        params: data
    })
}
