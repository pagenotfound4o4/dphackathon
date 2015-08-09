package org.emnets.dianping.network;

import org.emnets.dianping.model.Business;

import java.util.List;

import retrofit.RestAdapter;

public class SyncHelper {
    private static final String BASE_URL = "http://10.128.53.111:8080/takeme/favo";
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

    public List<Business> getAllFavourite(String uid, String state) {
        return getFavouriteService.getBusinessList(uid, state);
    }

    public List<Business> getBusinessListBySortedTime(String uid, String type, String order) {
        return getFavouriteService.getBusinessListBySortedTime(uid, type, order);
    }

    public List<Business> getBusinessListBySortedPrice(String uid, String type, String order) {
        return getFavouriteService.getBusinessListBySortedPrice(uid, type, order);
    }

    public List<Business> getBusinessListBySortedLocation(String uid, String type, double longitude,
                                                          double latitude) {
        return getFavouriteService.getBusinessListBySortedLocation(uid, type, longitude, latitude);
    }

    public List<Business> getBusinessListByFilterdPrice(String uid, String type, int minv, int maxv) {
        return getFavouriteService.getBusinessListByFilterdPrice(uid, type, minv, maxv);
    }
}
