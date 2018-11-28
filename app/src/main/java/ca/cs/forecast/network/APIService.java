package ca.cs.forecast.network;

import ca.cs.forecast.model.Weather.WeatherCity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("weather")
    Call<WeatherCity> fetchWeatherCity(@Query("id") int id, @Query("appid") String appid);

}
