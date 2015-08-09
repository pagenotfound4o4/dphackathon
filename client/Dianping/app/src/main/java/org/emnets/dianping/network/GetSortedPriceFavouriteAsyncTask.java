package org.emnets.dianping.network;

import android.os.AsyncTask;

import org.emnets.dianping.model.Business;
import org.emnets.dianping.ui.MainHomeFragment;

import java.util.List;

public class GetSortedPriceFavouriteAsyncTask extends AsyncTask<String, Void, List<Business>> {
    private MainHomeFragment fragment;
    private String uid;
    private String state;

    public GetSortedPriceFavouriteAsyncTask(MainHomeFragment fragment, String uid, String state) {
        this.fragment = fragment;
        this.uid = uid;
        this.state = state;
    }

    @Override
    protected List<Business> doInBackground(String... params) {
        return SyncHelper.getInstance().getBusinessListBySortedPrice(uid, state, params[0]);
    }

    @Override
    protected void onPostExecute(List<Business> businesses) {
        super.onPostExecute(businesses);
        fragment.updateData(businesses);
        fragment.getRefreshLayout().setRefreshing(false);
    }
}
