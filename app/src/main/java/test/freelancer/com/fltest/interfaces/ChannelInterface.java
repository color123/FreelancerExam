package test.freelancer.com.fltest.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.freelancer.com.fltest.models.ChannelResults;

/**
 * Created by user on 4/21/2016.
 */
public interface ChannelInterface {

    @GET("wabz/guide.php")
    Call<ChannelResults> channelResult(@Query("start") int index);

}
