package org.emnets.dianping.network;

import org.emnets.dianping.model.Business;

import java.util.List;

import retrofit.RestAdapter;

public class SyncHelper {
    private static final String BASE_URL = "http://10.128.53.109:8080/takeme/favo";
    private static SyncHelper inst = null;
    private RestAdapter restAdapter;
    private GetFavouriteBusinessService getFavouriteService;

    private SyncHelper() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
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
