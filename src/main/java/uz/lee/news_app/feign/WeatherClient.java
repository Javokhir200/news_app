package uz.lee.news_app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.lee.news_app.feignDtos.WeatherResponseDTO;

@FeignClient(name = "openWeatherMapClient", url = "https://api.openweathermap.org/data/2.5")
public interface WeatherClient {
    @GetMapping("/onecall")
    WeatherResponseDTO get7DayWeather(
        @RequestParam("lat") double latitude,
        @RequestParam("lon") double longitude,
        @RequestParam("exclude") String exclude,
        @RequestParam("appid") String apiKey

    );
}
