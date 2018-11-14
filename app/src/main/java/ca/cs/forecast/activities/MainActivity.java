package ca.cs.forecast.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.R;
import ca.cs.forecast.model.City;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        //Exemple d'utilisation de la base de donnée
        List<City> cities = ForecastApp.get().getDB().getCityDao().findCitiesForCountryCode("CA");
        cities.forEach( c -> Log.d(TAG, c.toString()));

    }
}
