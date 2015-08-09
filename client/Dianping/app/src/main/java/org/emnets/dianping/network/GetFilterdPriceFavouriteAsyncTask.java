package org.emnets.dianping.network;

import android.os.AsyncTask;

import org.emnets.dianping.model.Business;
import org.emnets.dianping.ui.MainHomeFragment;

import java.util.List;

public class GetFilterdPriceFavouriteAsyncTask extends AsyncTask<Integer, Void, List<Business>> {
    private MainHomeFragment fragment;
    private String uid;
    private String state;

    public GetFilterdPriceFavouriteAsyncTask(MainHomeFragment fragment, String uid, String state) {
        this.fragment = fragment;
        this.uid = uid;
        this.state = state;
    }

    @Override
    protected List<Business> doInBackground(Integer... params) {
        return SyncHelper.getInstance().getBusinessListByFilterdPrice(uid, state, params[0], params[1]);
    }

    @Override
    protected void onPostExecute(List<Business> businesses) {
        super.onPostExecute(businesses);
        fragment.updateData(businesses);
        fragment.getRefreshLayout().setRefreshing(false);
    }
}
