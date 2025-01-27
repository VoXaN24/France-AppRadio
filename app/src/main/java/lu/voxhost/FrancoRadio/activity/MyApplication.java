package lu.voxhost.FrancoRadio.activity;

import static lu.voxhost.FrancoRadio.services.MediaNotificationManager.NOTIFICATION_CHANNEL_ID;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.applovin.sdk.AppLovinSdk;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;

import java.util.Arrays;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        MobileAds.initialize(this, initializationStatus -> {
        });
        AudienceNetworkAds.initialize(this);
        AppLovinSdk.initializeSdk(this);
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.getInstance(this).getSettings().setTestDeviceAdvertisingIds(Arrays.asList("f6335ae2-79a7-4287-841e-ccad39f5134e"));
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Radio PLay Status", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

}