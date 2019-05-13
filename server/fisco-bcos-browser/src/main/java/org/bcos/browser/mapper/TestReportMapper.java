package org.bcos.browser.mapper;

import org.bcos.browser.entity.dto.TestReport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestReportMapper {
    List<TestReport> getTestReportList();
}
