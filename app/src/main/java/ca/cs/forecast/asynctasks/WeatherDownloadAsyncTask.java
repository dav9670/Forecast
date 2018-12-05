package ca.cs.forecast.asynctasks;

import android.os.AsyncTask;
import ca.cs.forecast.model.Weather.WeatherCity;
import ca.cs.forecast.network.RetrofitClient;
import ca.cs.forecast.utils.Constants;
import retrofit2.Call;

import java.io.IOException;

public class WeatherDownloadAsyncTask extends AsyncTask<Integer, Void, String> {
	private Callback callback;
	private WeatherCity weatherCity;

	public WeatherDownloadAsyncTask(Callback callback) {
		this.callback = callback;
	}

	@Override
	protected String doInBackground(Integer... integers) {
		int cityId = integers[0];

		Call<WeatherCity> weatherCityCall = RetrofitClient.getAPIService().fetchWeatherCity(cityId, Constants.OpenWeatherMap.APP_ID);
		try {
			this.weatherCity = weatherCityCall.execute().body();
			return "done";
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "error";
	}

	@Override
	protected void onPostExecute(String s) {
		if (s.equals("done")) {
			this.callback.onDownloadComplete(this.weatherCity);
		}
	}

	public interface Callback {
		void onDownloadComplete(WeatherCity weatherCity);
	}
}
