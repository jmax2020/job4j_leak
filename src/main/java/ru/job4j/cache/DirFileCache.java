package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key)  {
        String str = null;
        Path filePath = Path.of(String.format("%s/%s", cachingDir, key));
        try {
            if (filePath.toFile().exists()) {
                str =  Files.readString(filePath);
                put(key, str);
            }
        } catch (IOException e) {
            System.out.println(String.format(" File: s% doesn't exist!", key));
        }
        return str;
    }
}