package com.skandiacup.splinedevelopment.skandiacup.repository;

/**
 * Created by Jorgen on 28/10/15.
 */
public interface SoapRequestCallback {
    void successCallback(Object data);
    void errorCallback();
}