package org.bcos.browser.service;

import com.alibaba.fastjson.JSON;
import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.entity.dto.Contract;
import org.bcos.browser.entity.dto.Node;
import org.bcos.browser.mapper.ContractMapper;
import org.bcos.browser.mapper.NodeMapper;
import org.bcos.browser.util.JsonReadUtils;
import org.bcos.browser.util.ProcessUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HanService {

    @Autowired
    NodeMapper nodeMapper;

    @Autowired
    ContractMapper contractMapper;

    public BaseResponse getTimeStamp() {
        try {
            JSONObject json = JsonReadUtils.readJson("report.json");
            BaseResponse response = new BaseResponse(ConstantCode.SUCCESS);
            response.setData(json.get("timestamp"));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ConstantCode.SYSTEM_ERROR);
        }
    }

    public BaseResponse RoundName() {
        try {
            JSONObject json = JsonReadUtils.readJson("report.json");
            BaseResponse response = new BaseResponse(ConstantCode.SUCCESS);
            JSONArray array = json.getJSONArray("rounds");
            Map<String, String> map = new HashMap<>();
            for(int i = 0; i < array.length(); i ++) {
                JSONObject object = array.getJSONObject(i);
                map.put("index", Integer.toString(i));
                map.put("name", object.get("name").toString());
            }
            response.setData(map);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ConstantCode.SYSTEM_ERROR);
        }
    }

    public BaseResponse getJson() {
        JSONObject json = JsonReadUtils.readJson("report.json");
        try {
            BaseResponse response = new BaseResponse(ConstantCode.SUCCESS);
            response.setData(JSON.parseObject(json.toString()));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ConstantCode.SYSTEM_ERROR);
        }
    }

    public BaseResponse startShell() {
        try {
            BaseResponse response = new BaseResponse(ConstantCode.SUCCESS);
            response.setData(ProcessUtils.start("start_all.sh"));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ConstantCode.SYSTEM_ERROR);
        }
    }

    public BaseResponse stopShell(){
        try {
            BaseResponse response = new BaseResponse(ConstantCode.SUCCESS);
            response.setData(ProcessUtils.start("stop_all.sh"));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ConstantCode.SYSTEM_ERROR);
        }
    }

    public BaseResponse startTest(String groupId){
        try {
            String config1_name = "config1.json";
            JSONObject config1 = JsonReadUtils.readJson(config1_name);
            List<Node> lists = nodeMapper.getAllNode(Integer.parseInt(groupId));
            String rpc = lists.get(0).getRpcPort();
            String ip = lists.get(0).getIp();
            List<Contract> contracts = contractMapper.getContractList(0,10);
            JSONArray array = new JSONArray();
            for(Contract contract: contracts) {
                JSONObject object = new JSONObject();
                object.put("id", contract.getContractId());
                object.put("name", contract.getContractName());
                object.put("path", contract.getContractPath());
                object.put("language", "solidity");
                array.put(object);
            }
            JSONObject fisco = config1.getJSONObject("fiscoBCOS");
            JSONObject config = fisco.getJSONObject("config");
            config.put("proxy", rpc);
            JSONObject node = fisco.getJSONObject("node0");
            node.put("p2pIP", ip);
            fisco.put("smartContracts", array);
            JsonReadUtils.saveJson(config1_name, config1);
            return new BaseResponse(ConstantCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ConstantCode.SYSTEM_ERROR);
        }
    }
}
