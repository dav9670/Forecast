package ca.cs.forecast.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    @Expose
    private float mTemp;

    @SerializedName("pressure")
    @Expose
    private float mPressure;

    @SerializedName("humidity")
    @Expose
    private float mHumidity;

    @SerializedName("temp_min")
    @Expose
    private float mTempMin;

    @SerializedName("temp_max")
    @Expose
    private float mTempMax;


    public float getTemp() {
        return mTemp;
    }

    public float getPressure() {
        return mPressure;
    }

    public float getHumidity() {
        return mHumidity;
    }

    public float getTempMin() {
        return mTempMin;
    }

    public float getTempMax() {
        return mTempMax;
    }

    @Override
    public String toString() {
        return "Main{" +
                "mTemp=" + mTemp +
                ", mPressure=" + mPressure +
                ", mHumidity=" + mHumidity +
                ", mTempMin=" + mTempMin +
                ", mTempMax=" + mTempMax +
                '}';
    }
}
