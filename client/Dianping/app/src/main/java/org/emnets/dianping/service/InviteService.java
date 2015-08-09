package org.emnets.dianping.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import org.emnets.dianping.model.TimelineInfo;
import org.emnets.dianping.network.TimelineHelper;

public class InviteService extends Service {
    private Intent intent = new Intent("org.emnets.dianping.INVITE");
    private String uid;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("dp", "run here");
        uid = intent.getStringExtra("uid");
        Log.i("dp", "uid=" + uid);
        mHandler.post(runnable);
        return super.onStartCommand(intent, flags, startId);
    }

    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // request server
            TimelineInfo info = TimelineHelper.getInstance().checkInvite(uid);
            if (info.getFlag() == 1) {
                intent.putExtra("bid", info.getBid());
                sendBroadcast(intent);
            } else {
                mHandler.postDelayed(runnable, 5000);
            }
        }
    };
}
