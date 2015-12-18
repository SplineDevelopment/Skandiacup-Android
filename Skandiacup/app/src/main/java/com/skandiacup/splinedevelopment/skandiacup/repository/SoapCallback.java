package com.skandiacup.splinedevelopment.skandiacup.repository;

import java.util.ArrayList;

/**
 * Created by Jorgen on 27/10/15.
 */
public interface SoapCallback<T> {
    void successCallback(T data);
    void errorCallback();
}
