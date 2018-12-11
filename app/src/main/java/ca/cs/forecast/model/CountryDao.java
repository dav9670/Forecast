package ca.cs.forecast.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    LiveData<List<Country>> getAll();

    @Query("SELECT * FROM countries ORDER BY name")
    LiveData<List<Country>> getAllByName();

    @Query("SELECT * FROM countries ORDER BY continent")
    LiveData<List<Country>> getAllByContinent();

    @Query("SELECT * FROM countries ORDER BY population")
    LiveData<List<Country>> getAllByPopulation();

}
