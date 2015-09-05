package org.emnets.dianping.network;

import org.emnets.dianping.model.TimelineInfo;

import retrofit.RestAdapter;

public class TimelineHelper {
    private static final String BASE_URL = "http://192.168.191.3:8080/takeme/timeline";
    private static TimelineHelper inst = null;
    private RestAdapter restAdapter;
    private GetTimelineService service;

    private TimelineHelper() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();
        service = restAdapter.create(GetTimelineService.class);
    }

    public static TimelineHelper getInstance() {
        if (inst == null) {
            inst = new TimelineHelper();
        }
        return inst;
    }

    public void buy(String uid, String bid) {
        service.buy(uid, bid);
    }

    public TimelineInfo checkConfirm(String uid, String bid) {
        return service.checkConfirm(uid, bid);
    }

    public void confirm(String uid, String bid) {
        service.confirm(uid, bid);
    }

    public TimelineInfo checkInvite(String uid) {
        return service.checkInvite(uid);
    }
}
