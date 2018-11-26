package ca.cs.forecast.model;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Country.class, City.class}, version = 1)
public abstract class WorldDatabase extends RoomDatabase {
    public abstract CountryDao getCountryDao();

    public abstract CityDao getCityDao();
}
