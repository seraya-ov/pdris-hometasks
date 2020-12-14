package spring.entities;
import javax.persistence.*;


@Entity
@Table(name = "WEATHER")
public class Weather {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "City", length = 250, nullable = false)
    private String city;

    @Column(name = "Time", length = 64, nullable = false)
    private String time;

    @Column(name = "FeelsLike", nullable = false)
    private Double feelsLike;

    @Column(name = "Temperature", nullable = false)
    private Double temp;

    @Column(name = "WindSpeed", nullable = false)
    private Double windKph;

    @Column(name = "Pressure", nullable = false)
    private Double pressure;

    @Column(name = "WindDirection", nullable = false)
    private String windDir;

    @Column(name = "Humidity", nullable = false)
    private Integer humidity;


    public Weather(String time,
                   String city,
                   Double feelsLike, Double temp, Double windKph,
                   Double pressure, String windDir,
                   Integer humidity) {
        this.city = city;
        this.time = time;
        this.feelsLike = feelsLike;
        this.temp = temp;
        this.windKph = windKph;
        this.pressure = pressure;
        this.windDir = windDir;
        this.humidity = humidity;
    }

    public Weather() {
        this.time = "No data";
        this.city = "No data";
        this.feelsLike = 0.;
        this.temp = 0.;
        this.windKph = 0.;
        this.pressure = 0.;
        this.windDir = "No data";
        this.humidity = 0;
    }


    public String getTime() {
        return time;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getWindKph() {
        return windKph;
    }

    public Double getPressure() {
        return pressure;
    }

    public String getWindDir() {
        return windDir;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public String getCity() {
        return city;
    }

    public Long getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setWindKph(Double windKph) {
        this.windKph = windKph;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Time: " + time +
                "\n Feels like: " + feelsLike + "C" +
                "\n Temperature: " + temp + "C" +
                "\n Pressure " + pressure + "MB" +
                "\n Humidity: " + humidity + "%" +
                "\n Wind speed:" + windKph + "kph" +
                "\n Wind direction: '" + windDir;
    }
}
