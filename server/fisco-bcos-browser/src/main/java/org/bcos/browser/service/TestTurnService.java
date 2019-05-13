package org.bcos.browser.service;

import lombok.extern.slf4j.Slf4j;
import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.entity.dto.TestTurn;
import org.bcos.browser.mapper.TestTurnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestTurnService {
    @Autowired
    TestTurnMapper testTurnMapper;

    public BaseResponse getTestTurnList(){
        BaseResponse response=new BaseResponse(ConstantCode.SUCCESS);
        List<TestTurn> list=testTurnMapper.getTestTurnList();
        response.setData(list);
        return response;
    }
}
