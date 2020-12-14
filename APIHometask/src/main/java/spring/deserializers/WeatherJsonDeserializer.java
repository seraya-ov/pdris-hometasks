package spring.deserializers;

import org.json.JSONObject;
import spring.entities.Weather;

public class WeatherJsonDeserializer {
    public Weather deserialize(JSONObject json, String city) {
        JSONObject dayWeatherJson = (JSONObject) ((JSONObject) (json
                .getJSONObject("forecast")
                .getJSONArray("forecastday")
                .get(0))).getJSONArray("hour").get(12);
        String time = dayWeatherJson.getString("time");
        Double feelsLike = dayWeatherJson.getDouble("feelslike_c");
        Double temp = dayWeatherJson.getDouble("temp_c");
        Double windKph = dayWeatherJson.getDouble("wind_kph");
        Double pressure = dayWeatherJson.getDouble("pressure_mb");
        String windDir = dayWeatherJson.getString("wind_dir");
        Integer humidity = dayWeatherJson.getInt("humidity");
        return new Weather(time, city, feelsLike, temp, windKph, pressure, windDir, humidity);
    }
}
