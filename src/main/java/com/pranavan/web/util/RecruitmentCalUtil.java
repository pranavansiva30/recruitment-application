package com.pranavan.web.util;

/**
 * Created by pranavan on 7/19/18.
 */
public class RecruitmentCalUtil {

    public static Double getCostPerJob( Integer amountOfgroupPeople,Integer noOfGroups,Integer noOfInduvial,Double recruitCostPerMan,Double commission){
        Double costPerJob=noOfGroups*amountOfgroupPeople*recruitCostPerMan*(1+commission/100)+noOfInduvial*recruitCostPerMan;
        return costPerJob;

    }



}
