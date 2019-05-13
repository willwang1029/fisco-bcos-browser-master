package org.bcos.browser.mapper;

import org.bcos.browser.entity.dto.TestNode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestNodeMapper {
    List<TestNode> getTestNodeList();
}
