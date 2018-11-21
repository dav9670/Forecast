package ca.cs.forecast.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherCity {
    @SerializedName("coord")
    @Expose
    private Coord mCoord;

    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> mWeather = new ArrayList<>();

    @SerializedName("base")
    @Expose
    private String mBase;

    @SerializedName("main")
    @Expose
    private Main mMain;

    @SerializedName("visibility")
    @Expose
    private float mVisibility;

    @SerializedName("wind")
    @Expose
    private Wind mWind;

    @SerializedName("clouds")
    @Expose
    private Clouds mClouds;

    @SerializedName("dt")
    @Expose
    private float mDt;

    @SerializedName("sys")
    @Expose
    private Sys mSys;

    @SerializedName("id")
    @Expose
    private float mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("cod")
    @Expose
    private float mCod;


    public Coord getCoord() {
        return mCoord;
    }

    public ArrayList<Weather> getWeather() {
        return mWeather;
    }

    public String getBase() {
        return mBase;
    }

    public Main getMain() {
        return mMain;
    }

    public float getVisibility() {
        return mVisibility;
    }

    public Wind getWind() {
        return mWind;
    }

    public Clouds getClouds() {
        return mClouds;
    }

    public float getDt() {
        return mDt;
    }

    public Sys getSys() {
        return mSys;
    }

    public float getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public float getCod() {
        return mCod;
    }

    public String toString(){
        return "mCoord : Lat = " + mCoord.getLat() + " Long = " + mCoord.getLon();
    }
}