package ca.cs.forecast.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import ca.cs.forecast.R;
import ca.cs.forecast.data.CityViewModel;
import ca.cs.forecast.data.CountryViewModel;
import ca.cs.forecast.fragments.City.CityFragment;
import ca.cs.forecast.fragments.Country.CountryFragment;
import ca.cs.forecast.fragments.Weather.WeatherFragment;
import ca.cs.forecast.model.City;
import ca.cs.forecast.model.Country;

public class MainActivity extends AppCompatActivity implements CountryFragment.OnListFragmentInteractionListener, CityFragment.OnListFragmentInteractionListener {

    public final static String TAG = MainActivity.class.getSimpleName();
    private ImageView mImageView = null;
    private TextView mTitle = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mImageView = findViewById(R.id.city_flag_pictureView);
        mTitle = findViewById(R.id.toolbar_title);
        mTitle.setText(R.string.app_name);
        setTitle(null);

        if (savedInstanceState == null) {
            CountryFragment countryFragment = new CountryFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.activity_main_fragment, countryFragment).commit();
        }
    }

    @Override
    public void onCountryListFragmentInteraction(Country item) {
        ViewModelProviders.of(this).get(CountryViewModel.class).setSelectedItem(item);

        CityFragment cityFragment = new CityFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment, cityFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCityListFragmentInteraction(City item) {
        ViewModelProviders.of(this).get(CityViewModel.class).setSelectedItem(item);

        WeatherFragment weatherFragment = new WeatherFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment, weatherFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public ImageView getToolbarImageView() {
        return mImageView;
    }

    public TextView getToolbarTitle() {return mTitle;}
}
