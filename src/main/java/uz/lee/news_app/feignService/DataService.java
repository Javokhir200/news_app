package uz.lee.news_app.feignService;

import org.springframework.stereotype.Service;
import uz.lee.news_app.feign.APIFeign;
import uz.lee.news_app.feign.WeatherClient;
import uz.lee.news_app.feignDtos.APIResponseDTO;

@Service
public class DataService {
    private final APIFeign aplClient;
    private final WeatherClient weatherClient;

    public DataService(APIFeign aplClient, WeatherClient weatherClient) {
        this.aplClient = aplClient;
        this.weatherClient = weatherClient;
    }

    public APIResponseDTO getAPLData() {
        return aplClient.getAPIData();
    }
}
