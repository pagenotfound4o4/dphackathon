package org.emnets.dianping.network;

import org.emnets.dianping.model.Business;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

public interface GetFavouriteBusinessService {
    @GET("/users/{user}/favorite/{type}")
    List<Business> getBusinessList(@Path("user") String uid, @Path("type") String type);

    @GET("/users/{user}/favorite/{type}/sort/time/{order}")
    List<Business> getBusinessListBySortedTime(@Path("user") String uid, @Path("type") String type,
                                             @Path("order") String order);

    @GET("/users/{user}/favorite/{type}/sort/price/{order}")
    List<Business> getBusinessListBySortedPrice(@Path("user") String uid, @Path("type") String type,
                                          @Path("order") String order);

    @GET("/users/{user}/favorite/{type}/sort/location/{lng}/{lat}/asc")
    List<Business> getBusinessListBySortedLocation(@Path("user") String uid, @Path("type") String type,
                                                   @Path("lng") double longitude, @Path("lat") double latitude);

    @GET("/users/{user}/favorite/{type}/filter/price/{min}/{max}")
    List<Business> getBusinessListByFilterdPrice(@Path("user") String uid, @Path("type") String type,
                                                 @Path("min") int minv, @Path("max") int maxv);
}
