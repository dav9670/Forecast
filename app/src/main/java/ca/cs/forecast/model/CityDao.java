package ca.cs.forecast.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM cities WHERE country_code=:code")
    List<City> findCitiesForCountryCode(final String code);

}
