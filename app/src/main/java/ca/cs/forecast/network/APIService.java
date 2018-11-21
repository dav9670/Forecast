package ca.cs.forecast.network;

import java.util.List;

import ca.cs.forecast.model.Weather.WeatherCity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIService {

    @GET("weather")
    Call<WeatherCity> fetchWeatherCity(@Query("id") int id, @Query("appid") String appid);

}
