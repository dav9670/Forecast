package ca.cs.forecast.data;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.model.Country;

public class CountryViewModel extends GenericViewModel<Country> {

    @Override
    protected void loadData() {
        itemList.setValue(ForecastApp.get().getDB().getCountryDao().getAll());
    }
}
