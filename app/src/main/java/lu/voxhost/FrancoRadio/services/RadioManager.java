package lu.voxhost.FrancoRadio.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import lu.voxhost.FrancoRadio.utils.Log;
import lu.voxhost.FrancoRadio.utils.Tools;

public class RadioManager {
    private static RadioManager instance = null;
    private static RadioService service;
    private boolean serviceBound;

    private RadioManager() {
        serviceBound = false;
    }

    public static RadioManager with() {
        if (instance == null)
            instance = new RadioManager();
        return instance;
    }

    public static RadioService getService() {
        return service;
    }

    public void playOrPause(String streamUrl) {
        if (streamUrl == null) {
            service.stop();
        } else {
            service.playOrPause(streamUrl);
        }
    }

    public void ChangeFrequency(String streamUrl) {
        if (streamUrl == null) {
            service.stop();
        } else {
            service.stopAndPlay(streamUrl);
        }
    }

    public void stopServices() {
        service.stop();
    }

    public void destroyServices() {
        service.onDestroy();
    }

    public boolean isPlaying() {
        return service.isPlaying();
    }

    public void bind(Context context) {
        if (!serviceBound) {
            Intent intent = new Intent(context, RadioService.class);
            context.startService(intent);
            boolean bound = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            if (service != null)
                Tools.onEvent(service.getStatus());
        }
    }

    public void unbind(Context context) {
        if (serviceBound) {
            try {
                service.stop();
                context.unbindService(serviceConnection);
                context.stopService(new Intent(context, RadioService.class));
                serviceBound = false;
            } catch (IllegalArgumentException e) {
                Log.printStackTrace(e);
            }
        }
    }

    public boolean isBound() {
        return serviceBound;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            service = ((RadioService.LocalBinder) binder).getService();
            serviceBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            serviceBound = false;
        }
    };

}
