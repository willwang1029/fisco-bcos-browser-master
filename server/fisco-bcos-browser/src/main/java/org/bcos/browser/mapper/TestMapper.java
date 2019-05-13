package org.bcos.browser.mapper;

import org.springframework.stereotype.Repository;
import org.bcos.browser.entity.dto.Test;

import java.util.List;

@Repository
public interface TestMapper {
    List<Test> getTestList();
}
