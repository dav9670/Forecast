package ca.cs.forecast.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("id")
    @Expose
    private float mId;

    @SerializedName("main")
    @Expose
    private String mMain;

    @SerializedName("description")
    @Expose
    private String mDescription;

    @SerializedName("icon")
    @Expose
    private String mIcon;

    public float getId() {
        return mId;
    }

    public String getMain() {
        return mMain;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getIcon() {
        return mIcon;
    }
}
