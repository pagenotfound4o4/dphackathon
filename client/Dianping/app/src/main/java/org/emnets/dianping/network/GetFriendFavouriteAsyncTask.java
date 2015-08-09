package org.emnets.dianping.network;

import android.os.AsyncTask;

import org.emnets.dianping.model.Business;
import org.emnets.dianping.ui.MainActivity;
import org.emnets.dianping.ui.MainHomeFragment;

import java.util.List;

public class GetFriendFavouriteAsyncTask extends AsyncTask<String, Void, List<Business>> {
    private MainHomeFragment fragment;

    public GetFriendFavouriteAsyncTask(MainHomeFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected List<Business> doInBackground(String... params) {
        return SyncHelper.getInstance().getFriendFavourite(params[0]);
    }

    @Override
    protected void onPostExecute(List<Business> businesses) {
        super.onPostExecute(businesses);
        fragment.updateData(businesses);
        fragment.getRefreshLayout().setRefreshing(false);
    }
}
