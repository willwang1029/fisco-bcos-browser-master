package org.bcos.browser.controller;

import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.HanService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "han")
public class HanController {

    @Autowired
    HanService hservice;

    /**
     * getTimeStamp.
     * @return
     */
    @GetMapping("/getTimeStamp")
    public BaseResponse getGroupList() {
        return hservice.getTimeStamp();
    }

    /**
     * getRoundName.
     * @return
     */
    @GetMapping("/getRoundName")
    public BaseResponse getRoundName() {
        return hservice.RoundName();
    }

    /**
     * getJson.
     * @return
     */
    @GetMapping("/getJson")
    public BaseResponse getJson() {
        return hservice.getJson();
    }

    /**
     * startShell.
     * @return
     */
    @GetMapping("/startShell")
    public BaseResponse startShell() {
        return hservice.startShell();
    }

    @GetMapping("/stopShell")
    public BaseResponse stopShell(){
        return hservice.stopShell();
    }

    @PostMapping("/startTest")
    public BaseResponse startTest(String str) {
        return hservice.startTest(str);
    }

    @GetMapping("/jsonSave")
    public BaseResponse jsonSave(String userName) { return hservice.jsonSave(userName); }


    @GetMapping("/config")
    public BaseResponse config() { return hservice.config(); }
}
