package ca.cs.forecast.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    @Expose
    private float mAll;


    public float getAll() {
        return mAll;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "mAll=" + mAll +
                '}';
    }
}
