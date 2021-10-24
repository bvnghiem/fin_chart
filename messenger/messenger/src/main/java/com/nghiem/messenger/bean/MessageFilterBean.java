package com.nghiem.messenger.bean;

import jakarta.ws.rs.QueryParam;

public class MessageFilterBean {
    
    private @QueryParam("year") int year;
    private @QueryParam("startIndex") int startIndex;
    private @QueryParam("maxSize") int maxSize;
    
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
