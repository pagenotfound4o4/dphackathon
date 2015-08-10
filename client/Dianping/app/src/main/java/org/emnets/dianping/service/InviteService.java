package org.emnets.dianping.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import org.emnets.dianping.model.TimelineInfo;
import org.emnets.dianping.network.TimelineHelper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InviteService extends Service {
    private Intent intent = new Intent("org.emnets.dianping.INVITE");
    private String uid;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            uid = intent.getStringExtra("uid");
            scheduler.schedule(new InviteRunnable(uid), 0, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    class InviteRunnable implements Runnable {
        private String uid;

        public InviteRunnable(String uid) {
            this.uid = uid;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimelineInfo info = TimelineHelper.getInstance().checkInvite(uid);
                    if (info.getStatus() == 0) {
                        Log.i("dp", "bid=" + info.getInfo());
                        intent.putExtra("bid", String.valueOf(info.getInfo()));
                        sendBroadcast(intent);
                        break;
                    }
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
