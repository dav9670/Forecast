package ca.cs.forecast.utils;

/**
 * Created by guilmarc on 2016-12-01.
 */

public class Constants {
    public static final String BASE_FLAG_URL = "http://www.geognos.com/api/en/countries/flag/%1.png";
    public static final String BASE_WEATHER_URL = "http://openweathermap.org/img/w/%1.png";
    public static final String WILD_CARD = "%1";
    public static final String FLAG_SUBFOLDER = "/flags/";


    public static final float KELVIN = 273.15f;

    public static final String DATABASE_NAME = "forecast.sqlite";


    public class Retrofit {
        public final static String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    }

    public class OpenWeatherMap {
        public final static String APP_ID = "9b7b961a63bc4d3d5948d54ad21db4de";
        private final static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id=%1&APPID=%2";
    }

}
