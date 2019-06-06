package org.bcos.browser.entity.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Test {
    private int testId;
    private Timestamp testTime;
    private String username;
}
