package org.bcos.browser.controller;

import org.bcos.browser.base.BaseController;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="test")
public class TestController extends BaseController {
    @Autowired
    TestService testService;

    @GetMapping("/testList")
    public BaseResponse getTestList(String username){
        BaseResponse response=testService.getTestList(username);
        return response;
    }
}
