package org.bcos.browser.service;

import com.alibaba.fastjson.JSON;
import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.entity.dto.Contract;
import org.bcos.browser.entity.dto.Node;
import org.bcos.browser.entity.dto.Test;
import org.bcos.browser.mapper.ContractMapper;
import org.bcos.browser.mapper.NodeMapper;
import org.bcos.browser.mapper.TestMapper;
import org.bcos.browser.util.JsonReadUtils;
import org.bcos.browser.util.ProcessUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

@Service
public class HanService {

    @Autowired
    NodeMapper nodeMapper;

    @Autowired
    ContractMapper contractMapper;

    @Autowired
    TestMapper testMapper;

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
            String p2p = lists.get(0).getP2pPort();
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
            config.put("proxy", ip + ":" + rpc);
            JSONObject node = fisco.getJSONObject("node0");
            node.put("p2pIP", ip);
            fisco.put("smartContracts", array);
            JSONObject ports = fisco.getJSONObject("ports");
            ports.put("rpc", rpc);
            ports.put("p2p", p2p);
            JsonReadUtils.saveJson(config1_name, config1);
            return new BaseResponse(ConstantCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ConstantCode.SYSTEM_ERROR);
        }
    }

    public BaseResponse config2(List<String[]> list) {
        try {
            Map m1, test;
            Yaml yaml = new Yaml();
            File file = new File("./data/config2.yaml");

            //也可以将值转换为Map
            m1 = yaml.load(new FileInputStream(file));
            //通过map我们取值就可以了.
            test = (Map) m1.get("test");
            List<Map> rounds = new ArrayList<>();
            for(String[] data: list) {
                Map map = new LinkedHashMap();
                map.put("label", data[0]);   //名称

                int[] txDuration = new int[1];
                txDuration[0] = Integer.parseInt(data[1]);         // 间隔
                map.put("txDuration", txDuration);

                Map rateControl = new LinkedHashMap();
                rateControl.put("type", "fixed-rate");
                com.alibaba.fastjson.JSONObject opts = new com.alibaba.fastjson.JSONObject();
                opts.put("tps", Integer.parseInt(data[2]));       //  持续时间
                rateControl.put("opts", opts);
                List<Map> rate = new ArrayList<>();
                rate.add(rateControl);
                map.put("rateControl", rate);

                map.put("callback", data[3]);   //路径
                rounds.add(map);
            }
            test.put("rounds", rounds);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(yaml.dump(m1));
            fileWriter.flush();
            fileWriter.close();
            return new BaseResponse(ConstantCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(ConstantCode.SYSTEM_ERROR);
        }
    }

    public BaseResponse jsonSave(String userName) {
        if(saveTest(userName)) { return new BaseResponse(ConstantCode.SUCCESS); }
        else { return new BaseResponse(ConstantCode.SYSTEM_ERROR); }
    }


    public boolean saveTest(String userName) {
        try {
            JSONObject object = JsonReadUtils.readJson("report.json");
            Long timeStamp = Long.parseLong(object.get("timestamp").toString());

            Test test = new Test();
            test.setTestTime(new Timestamp(timeStamp));
            test.setTestResult(object.toString());
            test.setUserName(userName);
            testMapper.createTest();
            return testMapper.saveTestResult(test);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void testShell() {

    }
}
