package test.freelancer.com.fltest.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by user on 4/21/2016.
 */
public class Channel extends SugarRecord{

    @SerializedName("name")
    public String name;
    @SerializedName("start_time")
    public String startTime;
    @SerializedName("end_time")
    public String endTime;
    @SerializedName("channel")
    public String number;
    @SerializedName("rating")
    public String rating;

    public Channel(String name, String startTime, String endTime, String number, String rating) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.number = number;
        this.rating = rating;
    }

}
