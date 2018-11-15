package ca.cs.forecast.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("type")
    @Expose
    private float mType;

    @SerializedName("id")
    @Expose
    private float mId;

    @SerializedName("message")
    @Expose
    private float mMessage;

    @SerializedName("country")
    @Expose
    private String mCountry;

    @SerializedName("sunrise")
    @Expose
    private float mSunrise;

    @SerializedName("sunset")
    @Expose
    private float mSunset;


    public float getType() {
        return mType;
    }

    public float getId() {
        return mId;
    }

    public float getMessage() {
        return mMessage;
    }

    public String getCountry() {
        return mCountry;
    }

    public float getSunrise() {
        return mSunrise;
    }

    public float getSunset() {
        return mSunset;
    }
}
