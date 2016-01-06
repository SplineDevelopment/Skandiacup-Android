package com.skandiacup.splinedevelopment.skandiacup.repository;

public interface SoapRequestCallback {
    void successCallback(Object data);
    void errorCallback();
}
