package ca.cs.forecast.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIService {

    @Headers("user-key: 5146fa78acba47690b115d8f272bbac1")
    @GET("games/?filter[rating][gt]=50&limit=50&fields=*")
    Call<List<Game>> fetchGames();

}
