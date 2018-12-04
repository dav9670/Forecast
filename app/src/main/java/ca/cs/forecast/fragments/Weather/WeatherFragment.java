package ca.cs.forecast.fragments.Weather;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ca.cs.forecast.R;
import ca.cs.forecast.activities.MainActivity;
import ca.cs.forecast.data.CityViewModel;
import ca.cs.forecast.model.City;

public class WeatherFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    private City mCity;


    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        mCity = ViewModelProviders.of(getActivity()).get(CityViewModel.class).getSelectedItem();

        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);


        if (mCity != null) {
            ((MainActivity) getActivity()).getToolbarTitle().setText(getString(R.string.weather) + " - " + mCity.getName());
        } else {
            ((MainActivity) getActivity()).getToolbarTitle().setText(R.string.weather);
        }
        ((MainActivity) getActivity()).getToolbarImageView().setImageDrawable(getActivity().getApplicationInfo().loadIcon(getActivity().getPackageManager()));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pos = new LatLng(mCity.getLatitude(), mCity.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(pos)
                .title(mCity.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 10));
    }
}
