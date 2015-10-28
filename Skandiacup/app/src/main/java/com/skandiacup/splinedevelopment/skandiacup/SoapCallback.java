package com.skandiacup.splinedevelopment.skandiacup;

import java.util.ArrayList;

/**
 * Created by Jorgen on 27/10/15.
 */
public interface SoapCallback {
    void successCallback(Object data);
    void errorCallback();
}
