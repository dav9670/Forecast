package ca.cs.forecast.data;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.model.City;

public class CityViewModel extends GenericViewModel<City> {

    private String countryCode;

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    protected void loadData() {
        itemList.setValue(ForecastApp.get().getDB().getCityDao().findCitiesForCountryCode(countryCode));
    }
}
