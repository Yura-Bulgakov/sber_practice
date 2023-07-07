package com.practice.sber_practice.data.storage;

import com.practice.sber_practice.data.ProcessData;

public interface DataStore {

    ProcessData get(String parentId);

    boolean isPresent(String parentId);

    void put(String parentId, ProcessData data);

    boolean remove(String parentId);
}
