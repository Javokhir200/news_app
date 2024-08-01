package uz.lee.news_app.feignService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.lee.news_app.feign.WeatherClient;
import uz.lee.news_app.feignDtos.WeatherResponseDTO;

@Service
public class WeatherService {
    private final WeatherClient weatherClient;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public WeatherResponseDTO get7DayWeather(double latitude, double longitude) {
        return weatherClient.get7DayWeather(latitude, longitude, "minutely,hourly", apiKey);
    }
}
