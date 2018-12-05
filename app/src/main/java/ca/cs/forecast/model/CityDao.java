package ca.cs.forecast.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM cities WHERE country_code=:code")
    List<City> findCitiesForCountryCode(final String code);

    @Query("SELECT * FROM cities ORDER BY name")
    List<City> findAllCities();

}
