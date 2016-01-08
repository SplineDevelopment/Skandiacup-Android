/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.repository;

public interface SoapCallback<T> {
    void successCallback(T data);
    void errorCallback();
}
