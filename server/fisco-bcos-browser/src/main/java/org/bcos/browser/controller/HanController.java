package org.bcos.browser.controller;

import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.HanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

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

    @PostMapping("/upload")
    public void uploadExam(@RequestParam("file") MultipartFile file, String file_name, String label, String duration,
                           String tps) {
        try {
            String url = "./data/" + file_name;
            File dest = new File(url);
            if(!dest.getParentFile().exists()) { dest.getParentFile().mkdirs(); }
            if(!file.isEmpty()) { file.transferTo(dest); }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }

    @PostMapping("/config2")
    public BaseResponse config2(List<String[]> list){
        BaseResponse response=hservice.config2(list);
        return response;
    }
}
