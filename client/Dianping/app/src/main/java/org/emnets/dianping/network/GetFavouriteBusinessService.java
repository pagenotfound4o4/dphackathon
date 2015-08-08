package org.emnets.dianping.network;

import org.emnets.dianping.model.Business;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

public interface GetFavouriteBusinessService {
    @GET("users/{user}/favourite/{type}")
    List<Business> getBusinessList(@Path("user") String userId, @Path("type") String type);
}
