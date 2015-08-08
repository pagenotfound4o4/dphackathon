package org.emnets.dianping.network;

import org.emnets.dianping.model.Business;

import java.util.List;

import retrofit.RestAdapter;

public class SyncHelper {
    private static SyncHelper inst = null;
    private RestAdapter restAdapter;
    private GetFavouriteBusinessService getFavouriteService;

    private SyncHelper() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://10.10.0.10")
                .build();
        getFavouriteService = restAdapter.create(GetFavouriteBusinessService.class);
    }

    public static SyncHelper getInstance() {
        if (inst == null) {
            inst = new SyncHelper();
        }
        return inst;
    }

    public List<Business> getMyFavourite(String userId) {
        return getFavouriteService.getBusinessList(userId, "mine");
    }

    public List<Business> getFriendFavourite(String userId) {
        return getFavouriteService.getBusinessList(userId, "friend");
    }
}
