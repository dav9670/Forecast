package ca.cs.forecast.data;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.model.Country;

public class CountryViewModel extends GenericViewModel<Country> {

    SORT_MODE sortMode = SORT_MODE.DEFAULT;

    @Override
    protected void loadData() {
        switch (sortMode) {
            case DEFAULT:
                itemList.setValue(ForecastApp.get().getDB().getCountryDao().getAll());
                break;
            case NAME:
                itemList.setValue(ForecastApp.get().getDB().getCountryDao().getAllByName());
                break;
            case CONTINENT:
                itemList.setValue(ForecastApp.get().getDB().getCountryDao().getAllByContinent());
                break;
            case POPULATION:
                itemList.setValue(ForecastApp.get().getDB().getCountryDao().getAllByPopulation());
                break;
        }
    }

    public void setSortMode(SORT_MODE sortMode) {
        this.sortMode = sortMode;
        loadData();
    }

    public enum SORT_MODE {
        DEFAULT,
        NAME,
        CONTINENT,
        POPULATION
    }
}
