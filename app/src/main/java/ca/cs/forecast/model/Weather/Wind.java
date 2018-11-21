package ca.cs.forecast.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    @Expose
    private float mSpeed;
    @SerializedName("deg")
    @Expose
    private float mDeg;

    public float getSpeed() {
        return mSpeed;
    }

    public float getDeg() {
        return mDeg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "mSpeed=" + mSpeed +
                ", mDeg=" + mDeg +
                '}';
    }
}
