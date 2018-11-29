package ca.cs.forecast.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;

import ca.cs.forecast.R;
import ca.cs.forecast.data.CountryViewModel;
import ca.cs.forecast.fragments.CityFragment;
import ca.cs.forecast.fragments.CountryFragment;
import ca.cs.forecast.fragments.dummy.DummyContent;
import ca.cs.forecast.model.Country;

public class MainActivity extends AppCompatActivity implements CountryFragment.OnListFragmentInteractionListener, CityFragment.OnListFragmentInteractionListener {

    public final static String TAG = MainActivity.class.getSimpleName();
    private ImageView mImageView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mImageView = findViewById(R.id.city_flag_pictureView);
        setTitle(R.string.country);

        if(savedInstanceState == null){
            CountryFragment countryFragment = new CountryFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.activity_main_fragment, countryFragment).commit();
        }

        //Exemple d'utilisation de la base de donnée
        //List<City> cities = ForecastApp.get().getDB().getCityDao().findCitiesForCountryCode("CA");
        /*cities.forEach(
                c ->
                RetrofitClient.getAPIService().fetchWeatherCity(c.getId(), Constants.OpenWeatherMap.APP_ID).enqueue(new Callback<WeatherCity>() {
                    @Override
                    public void onResponse(Call<WeatherCity> call, Response<WeatherCity> response) {
                        //Si tout va bien, response.body contiendra le weatherCity pour chaque ville
                        Log.d(TAG, c.toString());
                        Log.d(TAG, "WeatherCity pour city id:" + c.getId() + " = " + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<WeatherCity> call, Throwable t) {
                        //Quand on a une problématique réseau
                        Log.d("Error", "Erreur de chargement de WeatherCity pour city id:" + c.getId());
                    }
                })
        );*/
    }

    @Override
    public void onCountryListFragmentInteraction(Country item) {
        //Ne fonctionne pas finalement
        //ViewModelProviders.of(this).get(CountryViewModel.class).setSelectedItem(item);
        CityFragment cityFragment = CityFragment.newInstance(item);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment, cityFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCityListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public ImageView getImageView(){
        return mImageView;
    }
}
