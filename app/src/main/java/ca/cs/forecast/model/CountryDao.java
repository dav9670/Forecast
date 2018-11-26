package ca.cs.forecast.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    List<Country> getAll();

}
