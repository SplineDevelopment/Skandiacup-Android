/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.repository;

public interface SoapRequestCallback {
    void successCallback(Object data);
    void errorCallback();
}
