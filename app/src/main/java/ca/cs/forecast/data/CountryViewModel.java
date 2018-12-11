package ca.cs.forecast.data;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.fragments.Country.CountryFragment;
import ca.cs.forecast.model.Country;

public class CountryViewModel extends GenericViewModel<Country> {

    SORT_MODE sortMode = SORT_MODE.DEFAULT;
    private Callback callback;

    /**
     * Set the sort mode to specify how cities will be loaded
     *
     * @param sortMode
     */
    public void setSortMode(SORT_MODE sortMode) {
        this.itemList = null;
        this.sortMode = sortMode;
    }

    /**
     * Set the callback to specify to who send data and load data
     *
     * @param callback
     */
    public void setCallback(Callback callback){
        itemList = null;
        this.callback = callback;
        loadData();
    }

    @Override
    protected void loadData() {
        switch (sortMode) {
            case DEFAULT:
                ForecastApp.get().getDB().getCountryDao().getAll().observe((CountryFragment)callback, new Observer<List<Country>>() {
                    @Override
                    public void onChanged(@Nullable List<Country> countries) {
                        callback.onCountryReceive(countries);
                    }
                });
                break;
            case NAME:
                ForecastApp.get().getDB().getCountryDao().getAllByName().observe((CountryFragment)callback, new Observer<List<Country>>() {
                    @Override
                    public void onChanged(@Nullable List<Country> countries) {
                        callback.onCountryReceive(countries);
                    }
                });
                break;
            case CONTINENT:
                ForecastApp.get().getDB().getCountryDao().getAllByContinent().observe((CountryFragment)callback, new Observer<List<Country>>() {
                    @Override
                    public void onChanged(@Nullable List<Country> countries) {
                        callback.onCountryReceive(countries);
                    }
                });
                break;
            case POPULATION:
                ForecastApp.get().getDB().getCountryDao().getAllByPopulation().observe((CountryFragment)callback, new Observer<List<Country>>() {
                    @Override
                    public void onChanged(@Nullable List<Country> countries) {
                        callback.onCountryReceive(countries);
                    }
                });
                break;
        }
    }

    public enum SORT_MODE {
        DEFAULT,
        NAME,
        CONTINENT,
        POPULATION
    }

    public interface Callback {
        /**
         * Sends information to the class responsible of displaying it
         *
         * @param countries    List of countries
         */
        void onCountryReceive(List<Country> countries);
    }
}
