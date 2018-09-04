package com.pranavan.web.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pranavan on 7/19/18.
 */
public class TestRecruitmentCalUtil {

    private Integer amountOfgroupPeople;
    private Integer noOfGroups;
    private Integer noOfInduvial;
    private Double recruitCostPerMan;
    private Double commission;
    @Before
    public void setUp() {

        amountOfgroupPeople=5;
        noOfGroups=1;
        noOfInduvial=1;
        recruitCostPerMan=200.0;
        commission=10.0;



    }
    @Test
    public void testGetCostPerJob(){
        Double actual=RecruitmentCalUtil.getCostPerJob(amountOfgroupPeople,noOfGroups,noOfInduvial,recruitCostPerMan,commission);
        assertEquals(new Double(actual),new Double(1300.0));

    }
}
