package org.bcos.browser.controller;

import org.bcos.browser.base.BaseController;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.TestTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "testturn")
public class TestTurnController extends BaseController {
    @Autowired
    TestTurnService testTurnService;

    @GetMapping("/testturnList")
    public BaseResponse getTestTurnList(){
        BaseResponse response=testTurnService.getTestTurnList();
        return response;
    }
}
