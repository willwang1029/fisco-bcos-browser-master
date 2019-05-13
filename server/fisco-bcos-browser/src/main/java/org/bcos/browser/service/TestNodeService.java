package org.bcos.browser.service;

import lombok.extern.slf4j.Slf4j;
import org.bcos.browser.base.ConstantCode;
import org.bcos.browser.entity.base.BaseResponse;
import org.bcos.browser.entity.dto.TestNode;
import org.bcos.browser.mapper.TestNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestNodeService {
    @Autowired
    TestNodeMapper testNodeMapper;

    public BaseResponse getTestNodeList(){
        BaseResponse response=new BaseResponse(ConstantCode.SUCCESS);
        List<TestNode> list=testNodeMapper.getTestNodeList();
        response.setData(list);
        return response;
    }
}
