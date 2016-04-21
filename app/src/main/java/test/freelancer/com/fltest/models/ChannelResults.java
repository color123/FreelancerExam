package test.freelancer.com.fltest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 4/21/2016.
 */
public class ChannelResults {

    @SerializedName("results")
    public List<Channel> results;

    @SerializedName("count")
    public int count;
}
