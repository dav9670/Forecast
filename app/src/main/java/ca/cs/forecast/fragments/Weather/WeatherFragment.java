package ca.cs.forecast.fragments.Weather;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import ca.cs.forecast.R;
import ca.cs.forecast.activities.MainActivity;
import ca.cs.forecast.asynctasks.WeatherDownloadAsyncTask;
import ca.cs.forecast.data.CityViewModel;
import ca.cs.forecast.model.City;
import ca.cs.forecast.model.Weather.Main;
import ca.cs.forecast.model.Weather.WeatherCity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.RequestCreator;

import java.util.Date;
import java.util.Objects;

public class WeatherFragment extends Fragment implements OnMapReadyCallback, WeatherDownloadAsyncTask.Callback {

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
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_weather, container, false);

		mCity = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(CityViewModel.class).getSelectedItem();

		mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		if (mMapFragment != null) {
			mMapFragment.getMapAsync(this);
		}

		if (mCity != null) {
			((MainActivity) getActivity()).getToolbarTitle().setText(String.format("%s - %s", getString(R.string.weather), mCity.getName()));
			WeatherDownloadAsyncTask asyncTask = new WeatherDownloadAsyncTask(this);
			asyncTask.execute(mCity.getId());
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

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public void onDownloadComplete(WeatherCity weatherCity, RequestCreator icon) {
		FragmentActivity activity = getActivity();
		if (activity != null) {
			Main main = weatherCity.getMain();

			// Views
			ImageView imageView = ((MainActivity) activity).getToolbarImageView();
			TextView temperature = activity.findViewById(R.id.temperature_textview);
			TextView description = activity.findViewById(R.id.description_textview);
			SeekBar pressure_bar = activity.findViewById(R.id.pression_progressBar);
			TextView pressure_text = activity.findViewById(R.id.pression_textview);
			SeekBar humidity_bar = activity.findViewById(R.id.humidity_progressBar);
			TextView humidity_text = activity.findViewById(R.id.humidity_textview);
			TextView sunrise = activity.findViewById(R.id.sunrise_textview);
			TextView sunset = activity.findViewById(R.id.sunset_textview);

			// Disable the onTouch event from the seekbars
			pressure_bar.setOnTouchListener((v, event) -> true);
			humidity_bar.setOnTouchListener((v, event) -> true);


			// Values
			String sTemp = String.format("%s°C", main.getTemp());
			String sDesc = weatherCity.getWeather().get(0).getDescription();

			int iPressure = (int) (main.getPressure());
			String sPressure = String.format("%skPa", main.getPressure() / 10);

			int iHumidity = (int) (main.getHumidity());
			String sHumidity = String.format("%s%%", main.getHumidity());

			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date sunrise_date = new Date((long) weatherCity.getSys().getSunrise() * 1000);
			Date sunset_date = new Date((long) weatherCity.getSys().getSunset() * 1000);

			String sSunrise = String.format("%s: %s", getString(R.string.sunrise), dateFormat.format(sunrise_date));
			String sSunset = String.format("%s: %s", getString(R.string.sunset), dateFormat.format(sunset_date));


			// Set Views' value
            icon.placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
			temperature.setText(sTemp);
			description.setText(sDesc);
			pressure_bar.setProgress(iPressure);
			pressure_text.setText(sPressure);
			humidity_bar.setProgress(iHumidity);
			humidity_text.setText(sHumidity);
			sunrise.setText(sSunrise);
			sunset.setText(sSunset);
		}
	}
}
