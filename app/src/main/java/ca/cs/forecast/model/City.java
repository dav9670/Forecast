package ca.cs.forecast.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "cities",
        foreignKeys = @ForeignKey(entity = Country.class,
                parentColumns = "code",
                childColumns = "country_code",
                onDelete = CASCADE),
        indices = {@Index(value = {"country_code"})})

public class City {

    @ColumnInfo(name = "id")
    @PrimaryKey
    private int mId;

    @ColumnInfo(name = "country_code")
    private String mCountryCode;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "latitude")
    private double mLatitude;

    @ColumnInfo(name = "longitude")
    private double mLongitude;

    public City(int id, String countryCode, String name, double latitude, double longitude) {
        this.mId = id;
        this.mCountryCode = countryCode;
        this.mName = name;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public int getId() {
        return mId;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public String getName() {
        return mName;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "mName='" + mName + '\'' +
                '}';
    }
}




