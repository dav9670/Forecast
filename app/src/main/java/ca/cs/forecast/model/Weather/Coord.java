package ca.cs.forecast.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    @Expose
    private float mLon;

    @SerializedName("lat")
    @Expose
    private float mLat;


    public float getLon() {
        return mLon;
    }

    public float getLat() {
        return mLat;
    }
}
