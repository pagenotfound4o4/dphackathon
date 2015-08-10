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

public class ConfirmService extends Service {
    private Intent intent = new Intent("org.emnets.dianping.CONFIRM");
    private String uid;
    private String bid;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        uid = intent.getStringExtra("uid");
        bid = intent.getStringExtra("bid");
        scheduler.schedule(new CheckRunnable(uid, bid), 0, TimeUnit.SECONDS);
        return super.onStartCommand(intent, flags, startId);
    }

    class CheckRunnable implements Runnable {
        private String uid, bid;

        public CheckRunnable(String uid, String bid) {
            this.uid = uid;
            this.bid = bid;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimelineInfo info = TimelineHelper.getInstance().checkConfirm(uid, bid);
                    if (info.getStatus() == 0) {
                        intent.putExtra("bid", bid);
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
