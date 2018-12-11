package ca.cs.forecast.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM cities WHERE country_code=:code")
    LiveData<List<City>> findCitiesForCountryCode(final String code);

    @Query("SELECT * FROM cities ORDER BY name")
    LiveData<List<City>> findAllCities();

    @Query("SELECT * FROM cities WHERE name LIKE '%' || :search || '%' AND country_code=:code ORDER BY name")
    LiveData<List<City>> findCitiesBySearchForCountryCode(final String search, final  String code);

    @Query("SELECT * FROM cities WHERE name LIKE '%' || :search || '%' ORDER BY name")
    LiveData<List<City>> findCitiesBySearchInAllCities(final String search);

}
