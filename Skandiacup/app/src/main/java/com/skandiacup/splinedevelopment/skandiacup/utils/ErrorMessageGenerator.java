package com.skandiacup.splinedevelopment.skandiacup.utils;

import android.content.Context;

import com.skandiacup.splinedevelopment.skandiacup.R;

import java.util.Random;

/**
 * Created by borgarlie on 20/12/15.
 */
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
