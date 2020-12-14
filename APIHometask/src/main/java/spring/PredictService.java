package spring;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;
import spring.entities.Currency;
import spring.entities.Weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class PredictService {

    private final CurrencyService currencyService;
    private final WeatherService weatherService;


    public PredictService(CurrencyService currencyService, WeatherService weatherService) {
        this.currencyService = currencyService;
        this.weatherService = weatherService;
    }

    public Weather predictWeather(String city) throws IOException {

        ArrayList<Weather> weathers = weatherService.getWeatherHistory(city, 8);
        SimpleRegression tempRegression = new SimpleRegression(true);
        SimpleRegression feelsLikeRegression = new SimpleRegression(true);
        SimpleRegression windKphRegression = new SimpleRegression(true);
        SimpleRegression pressureRegression = new SimpleRegression(true);
        SimpleRegression humidityRegression = new SimpleRegression(true);

        int maxIndex = weathers.size() - 1;

        for (int i = maxIndex; i > 0; --i) {
            tempRegression.addData(weathers.get(i).getTemp(), weathers.get(i - 1).getTemp());
            feelsLikeRegression.addData(weathers.get(i).getFeelsLike(), weathers.get(i - 1).getFeelsLike());
            windKphRegression.addData(weathers.get(i).getWindKph(), weathers.get(i - 1).getWindKph());
            pressureRegression.addData(weathers.get(i).getPressure(), weathers.get(i - 1).getPressure());
            humidityRegression.addData(weathers.get(i).getHumidity(), weathers.get(i - 1).getHumidity());
        }

        double temp = Precision.round(tempRegression.predict(weathers.get(0).getTemp()), 1);
        double feelsLike = Precision.round(feelsLikeRegression.predict(weathers.get(0).getFeelsLike()), 1);
        double windKph = Precision.round(windKphRegression.predict(weathers.get(0).getWindKph()), 1);
        double pressure = Precision.round(pressureRegression.predict(weathers.get(0).getPressure()), 0);
        double humidity = Precision.round(humidityRegression.predict(weathers.get(0).getHumidity()), 0);

        return new Weather(LocalDate.now().plusDays(1).toString(), city, feelsLike, temp, windKph, pressure, weathers.get(0).getWindDir(), Math.min((int) humidity, 100));
    }

    public Currency predictCurrency() throws IOException {
        ArrayList<Currency> currencies = currencyService.getCurrencyHistory(30);
        SimpleRegression gbrRegression = new SimpleRegression(true);
        SimpleRegression eurRegression = new SimpleRegression(true);
        SimpleRegression cadRegression = new SimpleRegression(true);
        SimpleRegression sgdRegression = new SimpleRegression(true);
        SimpleRegression chfRegression = new SimpleRegression(true);
        SimpleRegression audRegression = new SimpleRegression(true);
        SimpleRegression usdRegression = new SimpleRegression(true);

        int maxIndex = currencies.size() - 1;

        for (int i = maxIndex; i > 0; --i) {
            gbrRegression.addData(currencies.get(i).getGbr(), currencies.get(i - 1).getGbr());
            eurRegression.addData(currencies.get(i).getEur(), currencies.get(i - 1).getEur());
            cadRegression.addData(currencies.get(i).getCad(), currencies.get(i - 1).getCad());
            sgdRegression.addData(currencies.get(i).getSgd(), currencies.get(i - 1).getSgd());
            chfRegression.addData(currencies.get(i).getChf(), currencies.get(i - 1).getChf());
            audRegression.addData(currencies.get(i).getAud(), currencies.get(i - 1).getAud());
            usdRegression.addData(currencies.get(i).getUsd(), currencies.get(i - 1).getUsd());
        }

        double gbr = Precision.round(gbrRegression.predict(currencies.get(0).getGbr()), 4);
        double eur = Precision.round(gbrRegression.predict(currencies.get(0).getEur()), 4);
        double cad = Precision.round(gbrRegression.predict(currencies.get(0).getCad()), 4);
        double sgd = Precision.round(gbrRegression.predict(currencies.get(0).getSgd()), 4);
        double chf = Precision.round(gbrRegression.predict(currencies.get(0).getChf()), 4);
        double aud = Precision.round(gbrRegression.predict(currencies.get(0).getAud()), 4);
        double usd = Precision.round(gbrRegression.predict(currencies.get(0).getUsd()), 4);

        return new Currency(LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), gbr, eur, cad, sgd, chf, aud, usd);
    }


}
