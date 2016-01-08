/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.utils;

import android.content.Context;

import com.skandiacup.splinedevelopment.skandiacup.R;

import java.util.Random;

public class ErrorMessageGenerator {
    public static String getErrorMessage(Context context) {
        int rand = new Random().nextInt(2);
        switch (rand) {
            case 0:
                return context.getString(R.string.error_message_one);
            case 1:
                return context.getString(R.string.error_message_two);
            default:
                return context.getString(R.string.error_message_one);
        }
    }
}
