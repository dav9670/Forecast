package ca.cs.forecast.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    List<Country> getAll();

    @Query("SELECT * FROM countries ORDER BY name")
    List<Country> getAllByName();

    @Query("SELECT * FROM countries ORDER BY continent")
    List<Country> getAllByContinent();

    @Query("SELECT * FROM countries ORDER BY population")
    List<Country> getAllByPopulation();

    @Query("SELECT * FROM countries WHERE code = :code")
    Country getByCode(String code);

}
