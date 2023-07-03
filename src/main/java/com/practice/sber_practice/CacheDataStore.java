package com.practice.sber_practice;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Getter
public class CacheDataStore {
    // parentId, processData
    private final ConcurrentHashMap<String, ProcessData> processDataConcurrentHashMap;

    public CacheDataStore() {
        processDataConcurrentHashMap = new ConcurrentHashMap<>();
    }

}
