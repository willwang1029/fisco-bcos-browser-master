package org.bcos.browser.service;

import lombok.extern.slf4j.Slf4j;
import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.entity.dto.Test;
import org.bcos.browser.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public BaseResponse getTestList(String username){
        BaseResponse response = new BaseResponse(ConstantCode.SUCCESS);
        List<Test> list = testMapper.getTestList(username);
        response.setData(list);
        return response;
    }

    public BaseResponse getTestResult(int testId){
        BaseResponse response = new BaseResponse(ConstantCode.SUCCESS);
        Test testresult=testMapper.getTestResult(testId);
        response.setData(testresult);
        return response;
    }
}
