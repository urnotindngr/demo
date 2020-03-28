package com.github.jnglman.demo.second;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class TaskCacheTest {

    @Test
    void compute() throws ExecutionException, InterruptedException {
        TestObject testObject = Mockito.spy(new TestObject());
        TaskCache<String, String> taskCache = new TaskCache<>();
        Future<String> java = taskCache.compute("JAVA", testObject::addComma);
        java.get();
        Mockito.verify(testObject).addComma(anyString());

        Future<String> anotherOne = taskCache.compute("JAVA", testObject::addComma);
        anotherOne.get();
        assertSame(java, anotherOne);
        Mockito.verifyNoMoreInteractions(testObject);
    }

    public static class TestObject {
        public String addComma(String source) {
            return source.concat(",");
        }
    }
}