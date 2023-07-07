package com.practice.sber_practice.utils;

import java.math.BigDecimal;
import java.util.Random;

public abstract class RandomUtils {

    public static final Random RANDOM = new Random();


    public static String generateRandomString(int maxLength) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        int length = RANDOM.nextInt(maxLength) + 1;
        for (int i = 0; i < length; i++) {
            char c = chars[RANDOM.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
    public static BigDecimal generateRandomBigDecimal(int max) {
        return BigDecimal.valueOf(RANDOM.nextDouble() * max);
    }

}
