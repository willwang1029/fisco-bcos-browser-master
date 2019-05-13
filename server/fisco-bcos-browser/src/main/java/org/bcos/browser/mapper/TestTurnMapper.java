package org.bcos.browser.mapper;

import org.bcos.browser.entity.dto.TestTurn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestTurnMapper {
    List<TestTurn> getTestTurnList();
}
