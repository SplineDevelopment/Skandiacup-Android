package com.skandiacup.splinedevelopment.skandiacup.repository;


public interface FTPcallback<T> {
    void successCallback(T data);
    void errorCallback();
}
