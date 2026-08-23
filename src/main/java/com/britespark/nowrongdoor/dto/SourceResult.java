package com.britespark.nowrongdoor.dto;

public class SourceResult<T> {

    private T data;
    private boolean available;
    private String error;

    public SourceResult(T data, boolean available, String error) {
        this.data = data;
        this.available = available;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getError() {
        return error;
    }
}
