package ca.cs.forecast.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "countries",
        indices = {@Index(value = {"code"}, unique = true)})
public class Country {

    @ColumnInfo(name = "id")
    @PrimaryKey
    private int mId;

    @ColumnInfo(name = "code")
    @NonNull
    private String mCode;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "continent")
    private String mContinent;

    @ColumnInfo(name = "population")
    private long mPopulation;

    public Country(int id, String code, String name, String continent, long population) {
        this.mId = id;
        this.mCode = code;
        this.mName = name;
        this.mContinent = continent;
        this.mPopulation = population;
    }

    public int getId() {
        return mId;
    }

    public String getCode() {
        return mCode;
    }

    public String getName() {
        return mName;
    }

    public String getContinent() {
        return mContinent;
    }

    public long getPopulation() {
        return mPopulation;
    }
}
