package org.bcos.browser.service;

import lombok.extern.slf4j.Slf4j;
import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.entity.dto.TestReport;
import org.bcos.browser.mapper.TestReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestReportService {
    @Autowired
    TestReportMapper testReportMapper;

    public BaseResponse getTestReportList(){
        BaseResponse response=new BaseResponse(ConstantCode.SUCCESS);
        List<TestReport> list=testReportMapper.getTestReportList();
        response.setData(list);
        return response;
    }
}
