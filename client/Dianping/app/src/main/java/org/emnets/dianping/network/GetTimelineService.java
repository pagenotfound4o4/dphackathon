package org.emnets.dianping.network;

import org.emnets.dianping.model.TimelineInfo;

import retrofit.http.GET;
import retrofit.http.Path;

public interface GetTimelineService {
    @GET("/buy/{user}/{bid}")
    void buy(@Path("user") String uid, @Path("bid") String bid);

    @GET("/check/confirm/{uid}/{bid}")
    TimelineInfo checkConfirm(@Path("uid") String uid, @Path("bid") String bid);

    @GET("/confirm/{uid}/{bid}")
    void confirm(@Path("uid") String uid, @Path("bid") String bid);

    @GET("/check/invite/{uid}")
    TimelineInfo checkInvite(@Path("uid") String uid);
}
