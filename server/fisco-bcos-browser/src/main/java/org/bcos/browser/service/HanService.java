package org.bcos.browser.service;

import com.alibaba.fastjson.JSON;
import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.util.JsonReadUtils;
import org.bcos.browser.util.ProcessUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HanService {

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
}
