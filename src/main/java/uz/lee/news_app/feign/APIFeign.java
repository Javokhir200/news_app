package uz.lee.news_app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import uz.lee.news_app.feignDtos.APIResponseDTO;

@FeignClient(name = "apiClient", url = "http://api.com/api")
public interface APIFeign {
    @GetMapping()
    APIResponseDTO getAPIData();

}
