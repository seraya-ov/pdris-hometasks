package spring.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.entities.Weather;

import java.util.Optional;

public interface WeatherRepository extends CrudRepository<Weather, String> {
    Optional<Weather> getWeatherByCityAndTime(String city, String time);
}
