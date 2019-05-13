package org.bcos.browser.controller;

import org.bcos.browser.base.BaseController;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.service.TestReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "testreport")
public class TestReportController extends BaseController {
    @Autowired
    TestReportService testReportService;

    @GetMapping("/testreportList")
    public BaseResponse getTestReportList(){
        BaseResponse response=testReportService.getTestReportList();
        return response;
    }
}
