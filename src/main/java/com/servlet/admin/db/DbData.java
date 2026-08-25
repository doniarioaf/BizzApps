package com.servlet.admin.db;

import org.springframework.core.io.Resource;

import java.nio.file.Path;

public class DbData {
    private Resource resource;
    private String fileName;
    private Path path;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
