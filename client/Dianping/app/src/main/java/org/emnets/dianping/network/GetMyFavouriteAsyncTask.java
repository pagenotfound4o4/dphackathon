package org.emnets.dianping.network;

import android.os.AsyncTask;
import android.util.Log;

import org.emnets.dianping.model.Business;
import org.emnets.dianping.ui.MainActivity;
import org.emnets.dianping.ui.MainHomeFragment;

import java.util.List;

public class GetMyFavouriteAsyncTask extends AsyncTask<String, Void, List<Business>> {
    private MainHomeFragment fragment;

    public GetMyFavouriteAsyncTask(MainHomeFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected List<Business> doInBackground(String... params) {
        return SyncHelper.getInstance().getMyFavourite(params[0]);
    }

    @Override
    protected void onPostExecute(List<Business> businesses) {
        super.onPostExecute(businesses);
        Log.i("dp", "size=" + businesses.size());
        fragment.updateData(businesses);
        fragment.getRefreshLayout().setRefreshing(false);
    }
}
