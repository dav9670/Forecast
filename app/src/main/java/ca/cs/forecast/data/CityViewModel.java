package ca.cs.forecast.data;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.model.City;

public class CityViewModel extends GenericViewModel<City> {

    private String countryCode;

    /**
     * Set the country code to specify which cities will be loaded
     *
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        itemList = null;
        this.countryCode = countryCode;
    }

    @Override
    protected void loadData() {
       if(countryCode == "ALLCITY") {
           itemList.setValue(ForecastApp.get().getDB().getCityDao().findAllCities());
       }
       else{
           itemList.setValue(ForecastApp.get().getDB().getCityDao().findCitiesForCountryCode(countryCode));
        }
    }


}
