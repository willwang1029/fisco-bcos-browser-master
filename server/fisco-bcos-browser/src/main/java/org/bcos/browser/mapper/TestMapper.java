package org.bcos.browser.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.bcos.browser.entity.dto.Test;

import java.util.List;

@Repository
public interface TestMapper {
    List<Test> getTestList(@Param(value = "username")String username);

    Test getTestResult(@Param(value = "testId")int testId);

    Test getLatestTest(@Param(value = "username")String username);

    boolean saveTestResult(Test test);

    void createTest();
}
