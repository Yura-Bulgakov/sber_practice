package com.practice.sber_practice.utils;

import com.practice.sber_practice.pojo_totalsum.response.TotalSumRs;

public class TotalSumResponseGenerator {

    public static TotalSumRs generateTotalSumResponce(){
        TotalSumRs totalSumRs = new TotalSumRs();
        totalSumRs.setStatus(TotalSumRs.Status.OK);
        totalSumRs.setTotalSum(RandomUtils.generateRandomBigDecimal(100000));
        return totalSumRs;
    }
}
