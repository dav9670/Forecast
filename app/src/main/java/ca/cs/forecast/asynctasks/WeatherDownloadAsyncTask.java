package ca.cs.forecast.asynctasks;

import android.os.AsyncTask;
import ca.cs.forecast.ForecastApp;
import ca.cs.forecast.model.Weather.WeatherCity;
import ca.cs.forecast.network.RetrofitClient;
import ca.cs.forecast.utils.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import retrofit2.Call;

import java.io.IOException;
import java.util.Locale;

public class WeatherDownloadAsyncTask extends AsyncTask<Integer, Void, String> {
    private Callback callback;
	private WeatherCity weatherCity;
    private RequestCreator icon;

	public WeatherDownloadAsyncTask(Callback callback) {
	    this.callback = callback;
	}

	@Override
	protected String doInBackground(Integer... integers) {
		int cityId = integers[0];

		Call<WeatherCity> weatherCityCall = RetrofitClient.getAPIService().fetchWeatherCity(cityId, Constants.OpenWeatherMap.APP_ID, Locale.getDefault().getLanguage());
		try {
			this.weatherCity = weatherCityCall.execute().body();

            if (weatherCity != null) {
                String url = Constants.BASE_WEATHER_URL;
                url = url.replace(Constants.WILD_CARD, weatherCity.getWeather().get(0).getIcon());
                this.icon = Picasso.with(ForecastApp.get().getApplicationContext()).load(url);
            }
            return "done";
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "error";
	}

	@Override
	protected void onPostExecute(String s) {
		if (s.equals("done")) {
			this.callback.onDownloadComplete(this.weatherCity, icon);
		}
	}

	public interface Callback {
		/**
		 * Sends information to the class responsible of displaying it
		 *
		 * @param weatherCity Contains all the information needed for the Weather
		 * @param icon        The weather's icon
		 */
		void onDownloadComplete(WeatherCity weatherCity, RequestCreator icon);
	}
}
