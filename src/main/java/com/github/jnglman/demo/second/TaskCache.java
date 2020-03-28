package com.github.jnglman.demo.second;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class TaskCache<K, V> {
    private final ConcurrentHashMap<K, Future<V>> map = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public Future<V> compute(K k, Function<K, V> f) {
        return map.computeIfAbsent(k, k1 -> CompletableFuture.supplyAsync(() -> f.apply(k1), executorService));
    }
}
