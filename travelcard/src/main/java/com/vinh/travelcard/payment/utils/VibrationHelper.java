package com.vinh.travelcard.payment.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Vibrator;

public class VibrationHelper {

    @SuppressLint("MissingPermission")
    public static void vibrate(Context context, int duration) {
        if (hasVibrationPermission(context)) {
            ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(duration);
        }
    }

    public static boolean hasVibrationPermission(Context context) {
        // temporary workaround until https://github.com/robolectric/robolectric/pull/2047 is released
        try {
            return (context.getPackageManager().checkPermission(Manifest.permission.VIBRATE,
                    context.getPackageName()) == PackageManager.PERMISSION_GRANTED);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
