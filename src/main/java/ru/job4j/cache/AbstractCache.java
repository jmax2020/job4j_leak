package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        if (!cache.containsKey(key) || cache.get(key).get() == null) {
            V file = load(key);
            if (file != null) {
                cache.put(key, new SoftReference<>(file));
            }
        }
        return cache.get(key).get();
    }

    protected abstract V load(K key);
}