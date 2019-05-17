package org.bcos.browser.controller;

import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.HanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
