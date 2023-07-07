package com.practice.sber_practice.utils;

public class RandomParentIdGenerator {


    public static String getParentId(int maxLength){
        String parentId = RandomUtils.generateRandomString(maxLength);
        return parentId;
    }
}
