package org.emnets.dianping.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import org.emnets.dianping.model.TimelineInfo;
import org.emnets.dianping.network.TimelineHelper;

public class ConfirmService extends Service {
    private Intent intent = new Intent("org.emnets.dianping.CONFIRM");
    private String uid;
    private String bid;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        uid = intent.getStringExtra("uid");
        bid = intent.getStringExtra("bid");
        mHandler.post(runnable);
        return super.onStartCommand(intent, flags, startId);
    }

    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // request status
            TimelineInfo info = TimelineHelper.getInstance().checkConfirm(uid, bid);
            Log.i("dp", "execute handler in pull service");

            // send broadcast
            if (info.getFlag() == 1) {
                intent.putExtra("bid", info.getBid());
                sendBroadcast(intent);
            } else {
                mHandler.postDelayed(runnable, 5000);
            }
        }
    };
}
