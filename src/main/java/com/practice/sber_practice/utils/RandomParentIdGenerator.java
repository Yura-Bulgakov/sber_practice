package com.practice.sber_practice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomParentIdGenerator {

    public static String getParentId(int maxLength){
        String parentId = RandomUtils.generateRandomString(maxLength);
        return parentId;
    }
}
