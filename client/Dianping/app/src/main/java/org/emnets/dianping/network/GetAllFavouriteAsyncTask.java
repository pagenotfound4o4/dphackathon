package org.emnets.dianping.network;

import android.os.AsyncTask;
import android.util.Log;

import org.emnets.dianping.model.Business;
import org.emnets.dianping.ui.MainActivity;
import org.emnets.dianping.ui.MainHomeFragment;

import java.util.List;

public class GetAllFavouriteAsyncTask extends AsyncTask<Void, Void, List<Business>> {
    private MainHomeFragment fragment;
    private String uid;
    private String state;

    public GetAllFavouriteAsyncTask(MainHomeFragment fragment, String uid, String state) {
        this.fragment = fragment;
        this.uid = uid;
        this.state = state;
    }

    @Override
    protected List<Business> doInBackground(Void... params) {
        return SyncHelper.getInstance().getAllFavourite(uid, state);
    }

    @Override
    protected void onPostExecute(List<Business> businesses) {
        super.onPostExecute(businesses);
        fragment.updateData(businesses);
        fragment.getRefreshLayout().setRefreshing(false);
    }
}
