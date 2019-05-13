package org.bcos.browser.entity.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class TestReport {
    private int testId;
    private Timestamp testTime;
}
