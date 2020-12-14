package spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import spring.entities.Weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final PredictService predictService;

    public WeatherController(WeatherService weatherService, PredictService predictService) {
        this.weatherService = weatherService;
        this.predictService = predictService;
    }

    @GetMapping("/{city}/{days}")
    ModelAndView getWeather(@PathVariable String city, @PathVariable int days) throws IOException {
        ArrayList<Weather> weatherHistory = weatherService.getWeatherHistory(city, days);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weather");
        modelAndView.addObject("weather", weatherHistory);
        modelAndView.addObject("city", city);
        modelAndView.addObject("action", "History");
        return modelAndView;
    }

    @GetMapping({"/{cityOrDays}", "/{cityOrDays}/"})
    ModelAndView getWeather(@PathVariable String cityOrDays) throws IOException {
        try {
            int days = Integer.parseInt(cityOrDays);
            return getWeather("Moscow", days);
        }
        catch(NumberFormatException e) {
            return getWeather(cityOrDays, 1);
        }
    }

    @GetMapping({"", "/"})
    ModelAndView getWeather() throws IOException {
        return getWeather("Moscow", 1);
    }

    @GetMapping({"/{city}/predict"})
    ModelAndView predictWeather(@PathVariable String city) throws IOException {
        ArrayList<Weather> prediction = new ArrayList<>(Collections.singletonList(predictService.predictWeather(city)));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("weather");
        modelAndView.addObject("weather", prediction);
        modelAndView.addObject("city", city);
        modelAndView.addObject("action", "Forecast");
        return modelAndView;
    }

    @GetMapping({"/predict"})
    ModelAndView predictWeather() throws IOException {
        return predictWeather("Moscow");
    }
}
