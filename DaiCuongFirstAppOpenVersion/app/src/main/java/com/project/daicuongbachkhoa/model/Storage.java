package com.project.daicuongbachkhoa.model;

public class Storage {
    private String
            nameStorage,
            dateStorage,
            contentStorage;
    private int pointStorage;

    public Storage() {
    }

    public Storage(String nameStorage, int pointStorage, String dateStorage, String contentStorage) {
        this.nameStorage = nameStorage;
        this.dateStorage = dateStorage;
        this.contentStorage = contentStorage;
        this.pointStorage = pointStorage;
    }

    public String getNameStorage() {
        return nameStorage;
    }

    public void setNameStorage(String nameStorage) {
        this.nameStorage = nameStorage;
    }

    public String getDateStorage() {
        return dateStorage;
    }

    public void setDateStorage(String dateStorage) {
        this.dateStorage = dateStorage;
    }

    public String getContentStorage() {
        return contentStorage;
    }

    public void setContentStorage(String contentStorage) {
        this.contentStorage = contentStorage;
    }

    public int getPointStorage() {
        return pointStorage;
    }

    public void setPointStorage(int pointStorage) {
        this.pointStorage = pointStorage;
    }
}
