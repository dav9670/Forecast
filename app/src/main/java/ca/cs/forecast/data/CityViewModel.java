package ca.cs.forecast.data;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.fragments.City.CityFragment;
import ca.cs.forecast.model.City;

public class CityViewModel extends GenericViewModel<City> {

    private SEARCH_MODE searchMode = SEARCH_MODE.NONE;
    private String countryCode;
    private String searchText;
    private Callback callback;

    /**
     * Set the country code to specify which cities will be loaded
     *
     * @param countryCode
     */

    public void setCountryCode(String countryCode) {
        itemList = null;
        this.countryCode = countryCode;
    }

    /**
     * Set the search mode to specify how cities will be loaded
     *
     * @param searchMode
     */
    public void setSearchMode(SEARCH_MODE searchMode) {
        itemList = null;
        this.searchMode = searchMode;
    }

    /**
     * Set the search mode to specify which cities will be loaded
     *
     * @param searchText
     */
    public void setSearchText(String searchText) {
        itemList = null;
        this.searchText = searchText;
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

        if(callback == null)
            return;

        switch (searchMode){
            case NONE:
                if(countryCode == "ALLCITY") {
                    ForecastApp.get().getDB().getCityDao().findAllCities().observe((CityFragment)callback, new Observer<List<City>>() {
                        @Override
                        public void onChanged(@Nullable List<City> cities) {
                            callback.onCityReceive(cities);
                        }
                    });
                }
                else{
                    ForecastApp.get().getDB().getCityDao().findCitiesForCountryCode(countryCode).observe((CityFragment)callback, new Observer<List<City>>() {
                        @Override
                        public void onChanged(@Nullable List<City> cities) {
                            callback.onCityReceive(cities);
                        }
                    });
                }
                break;
            case NAME:
                if(countryCode == "ALLCITY") {
                    ForecastApp.get().getDB().getCityDao().findCitiesBySearchInAllCities(searchText).observe((CityFragment)callback, new Observer<List<City>>() {
                        @Override
                        public void onChanged(@Nullable List<City> cities) {
                            callback.onCityReceive(cities);
                        }
                    });
                }
                else{
                    ForecastApp.get().getDB().getCityDao().findCitiesBySearchForCountryCode(searchText, countryCode).observe((CityFragment)callback, new Observer<List<City>>() {
                        @Override
                        public void onChanged(@Nullable List<City> cities) {
                            callback.onCityReceive(cities);
                        }
                    });
                }
                break;
        }
    }

    public enum SEARCH_MODE {
        NONE,
        NAME
    }

    public interface Callback {
        /**
         * Sends information to the class responsible of displaying it
         *
         * @param cities    List of cities
         */
        void onCityReceive(List<City> cities);
    }

}
