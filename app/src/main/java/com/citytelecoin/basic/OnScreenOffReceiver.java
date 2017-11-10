package com.citytelecoin.basic;

/**
 * Created by carlosp on 11/8/17.
 */



/**
 THis code works to disable the use of the power button
 on long and short press.  Short press brings it back
 while long press is not allowed.
 */


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public class OnScreenOffReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
            AppContext ctx = (AppContext) context.getApplicationContext();
            // is Kiosk Mode active?
            if(PrefUtils.isKioskModeActive(ctx)) {
                //wakeUpDevice(ctx);
            }
        }
    }

    private void wakeUpDevice(AppContext context) {
        PowerManager.WakeLock wakeLock = context.getWakeLock(); // get WakeLock reference via AppContext
        if (wakeLock.isHeld()) {
            wakeLock.release(); // release old wake lock
        }

        // create a new wake lock...
        wakeLock.acquire(1*60*1000L /*10 minutes*/);

        // ... and release again
        wakeLock.release();
    }


}