package com.practice.sber_practice.data.storage;

import com.practice.sber_practice.data.ProcessData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Getter
@EqualsAndHashCode
public class CacheDataStore implements DataStore, Serializable {
    // parentId, processData
    private final ConcurrentHashMap<String, ProcessData> processDataConcurrentHashMap;

    public CacheDataStore() {
        processDataConcurrentHashMap = new ConcurrentHashMap<>();
    }
    public ProcessData get(String parentId){
        if (parentId == null || parentId.isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть пустым или равным null");
        }
        ProcessData processData = processDataConcurrentHashMap.get(parentId);
        if (processData == null) {
            throw new NoSuchElementException("Данные не найдены для ключа: " + parentId);
        }
        return processData;
    }

    public boolean isPresent(String parentId){
        if (parentId == null) {
            return false;
        }
        return processDataConcurrentHashMap.containsKey(parentId);
    }
    public void put(String parentId, ProcessData data)
    {
        if (parentId == null || parentId.isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть пустым или равным null");
        }

        if (data == null) {
            throw new IllegalArgumentException("Data не может быть равным null");
        }

        processDataConcurrentHashMap.put(parentId, data);
    }
    public boolean remove(String parentId){
        if (parentId == null) {
            return false;
        }
        return processDataConcurrentHashMap.remove(parentId) != null;
    }

}
