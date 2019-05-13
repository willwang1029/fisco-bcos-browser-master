package org.bcos.browser.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestTurn {
    private int testTurnId;
    private String testName;
    private int succNum;
    private int failNum;
    private String contractScript;
    private float sendRate;
    private float maxLatency;
    private float minLatency;
    private float throughPut;
    private int testId;
}
