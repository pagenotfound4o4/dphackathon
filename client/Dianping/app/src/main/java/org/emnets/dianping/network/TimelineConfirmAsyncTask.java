package org.emnets.dianping.network;

import android.os.AsyncTask;

public class TimelineConfirmAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        TimelineHelper.getInstance().checkConfirm(params[0], params[1]);
        return null;
    }
}
