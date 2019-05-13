package org.bcos.browser.entity.dto;

import lombok.Data;

@Data
public class TestNode {
    private int testNodeId;
    private String nodeType;
    private float cpuMax;
    private float cpuAvg;
    private float memoryMax;
    private float memoryAvg;
    private float discRead;
    private float discWrite;
    private float trafficIn;
    private float trafficOut;
    private int testTurnId;
}
