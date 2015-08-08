package org.emnets.dianping.network;

import android.os.AsyncTask;

import org.emnets.dianping.model.Business;
import org.emnets.dianping.ui.MainActivity;

import java.util.List;

public class GetMyFavouriteAsyncTask extends AsyncTask<String, Void, List<Business>> {
    private MainActivity activity;

    public GetMyFavouriteAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected List<Business> doInBackground(String... params) {
        return SyncHelper.getInstance().getMyFavourite(params[0]);
    }

    @Override
    protected void onPostExecute(List<Business> businesses) {
        super.onPostExecute(businesses);
        activity.setMyFavouriteList(businesses);
    }
}
