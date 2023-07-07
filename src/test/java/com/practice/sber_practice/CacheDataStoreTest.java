package com.practice.sber_practice;

import com.practice.sber_practice.data.storage.CacheDataStore;
import com.practice.sber_practice.data.ProcessData;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


class CacheDataStoreTest {

    @Test
    void testGetWithDataExists() {
        CacheDataStore cacheDataStore = new CacheDataStore();
        String validKey = "validKey";
        ProcessData processData = ProcessData.builder().build();
        cacheDataStore.put(validKey, processData);

        ProcessData result = cacheDataStore.get(validKey);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(processData, result);
    }

    @Test
    void testGetWithEmptyKey() {
        CacheDataStore cacheDataStore = new CacheDataStore();
        String emptyKey = "";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> cacheDataStore.get(emptyKey));
        Assertions.assertEquals("Ключ не может быть пустым или равным null", exception.getMessage());
    }

    @Test
    void testGetWithNullKey() {
        CacheDataStore cacheDataStore = new CacheDataStore();
        String nullKey = null;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> cacheDataStore.get(nullKey));
        Assertions.assertEquals("Ключ не может быть пустым или равным null", exception.getMessage());
    }

    @Test
    void testGetWithNonExistingData() {
        CacheDataStore cacheDataStore = new CacheDataStore();
        String nonExistingKey = "nonExistingKey";

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class,
                () -> cacheDataStore.get(nonExistingKey));
        Assertions.assertEquals("Данные не найдены для ключа: " + nonExistingKey, exception.getMessage());
    }



    @Test
    void isPresent() {

        @AllArgsConstructor
        class TestCase{
            Map<String, ProcessData> dataToPutInStore;
            String keyToCheckIsPresent;
            boolean expectedResult;

            void run(){
                CacheDataStore cacheDataStore = new CacheDataStore();
                cacheDataStore.getProcessDataConcurrentHashMap().putAll(dataToPutInStore);
                boolean actualResult = cacheDataStore.isPresent(keyToCheckIsPresent);
                String message = String.format("Ожидаемый результат %b не совпал фактическим %b", expectedResult , actualResult);
                Assertions.assertTrue(actualResult == expectedResult, message);
            }
        }

        ProcessData data = ProcessData.builder().build();
        String key = "12gh";
        Map<String, ProcessData> map1 = new HashMap<>();
        Map<String, ProcessData> map2 = new HashMap<>();
        map2.put(key, data);


        TestCase[] testCases = new TestCase[]{
                new TestCase(map1, key, false),
                new TestCase(map2, key, true),
                new TestCase(map1, "", false),
                new TestCase(map2, "", false),
                new TestCase(map1, null, false),
                new TestCase(map2, null, false),
        };
        for (TestCase test : testCases) {
            test.run();
        }
    }


    @Test
    void testPutWithValidData() {
        CacheDataStore cacheDataStore = new CacheDataStore();
        String validKey = "validKey";
        ProcessData validData = ProcessData.builder().build();

        Assertions.assertDoesNotThrow(() -> cacheDataStore.put(validKey, validData));
    }

    @Test
    void testPutWithNullKey() {
        CacheDataStore cacheDataStore = new CacheDataStore();
        String nullKey = null;
        ProcessData validData = ProcessData.builder().build();

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> cacheDataStore.put(nullKey, validData));
        Assertions.assertEquals("Ключ не может быть пустым или равным null", exception.getMessage());
    }

    @Test
    void testPutWithEmptyKey() {
        CacheDataStore cacheDataStore = new CacheDataStore();
        String emptyKey = "";
        ProcessData validData = ProcessData.builder().build();

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> cacheDataStore.put(emptyKey, validData));
        Assertions.assertEquals("Ключ не может быть пустым или равным null", exception.getMessage());
    }

    @Test
    void testPutWithNullData() {
        CacheDataStore cacheDataStore = new CacheDataStore();
        String validKey = "validKey";
        ProcessData nullData = null;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> cacheDataStore.put(validKey, nullData));
        Assertions.assertEquals("Data не может быть равным null", exception.getMessage());
    }

    @Test
    void remove() {
        @AllArgsConstructor
        class TestCase{
            Map<String, ProcessData> dataToPutInStore;
            String keyToRemoveMapping;
            boolean expectedResult;

            void run(){
                CacheDataStore cacheDataStore = new CacheDataStore();
                cacheDataStore.getProcessDataConcurrentHashMap().putAll(dataToPutInStore);
                boolean actualResult = cacheDataStore.remove(keyToRemoveMapping);
                String message = String.format("Ожидаемый результат %b не совпал фактическим %b", expectedResult , actualResult);
                Assertions.assertTrue(actualResult == expectedResult, message);
            }
        }

        ProcessData data = ProcessData.builder().build();
        String key = "12gh";
        Map<String, ProcessData> map1 = new HashMap<>();
        Map<String, ProcessData> map2 = new HashMap<>();
        map2.put(key, data);


        TestCase[] testCases = new TestCase[]{
                new TestCase(map1, key, false),
                new TestCase(map2, key, true),
                new TestCase(map1, "", false),
                new TestCase(map2, "", false),
                new TestCase(map1, null, false),
                new TestCase(map2, null, false),
        };
        for (TestCase test : testCases) {
            test.run();
        }
    }
}