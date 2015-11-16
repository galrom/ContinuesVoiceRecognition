package com.continuesvoicerecognition;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;


/**
 * Created by root on 19/10/15.
 */
public class PermissionHandler {


    public final static int RECORD_AUDIO=1;


    public static void askForPermission(int which,final Activity activity)
    {
        if(Build.VERSION.SDK_INT<23)
        {
            return;
        }
        else //We are running on Android M
        {
            switch(which)
            {
                case RECORD_AUDIO:
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.GET_ACCOUNTS)== PackageManager.PERMISSION_GRANTED  )
                        return;
                    else
                    {
                        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO))
                        {
                            Toast.makeText(activity,activity.getString(R.string.record_audio_is_required),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, RECORD_AUDIO);
                        }
                    }
                    break;
            }
        }
    }

    public static boolean checkPermission(Activity activity,int which)
    {
        if(Build.VERSION.SDK_INT<23)
        {
            return true;
        }
        else {
            switch (which) {
                case RECORD_AUDIO:
                    return ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED;
            }
        }
        return false;
    }

}
