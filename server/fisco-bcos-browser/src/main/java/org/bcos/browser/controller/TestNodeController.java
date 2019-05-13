package org.bcos.browser.controller;

import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.TestNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "testnode")
public class TestNodeController {
    @Autowired
    TestNodeService testNodeService;

    @GetMapping("/testnodeList")
    public BaseResponse getTestNodeList(){
        BaseResponse response=testNodeService.getTestNodeList();
        return response;
    }
}
