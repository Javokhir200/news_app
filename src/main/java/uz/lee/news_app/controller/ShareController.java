package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.lee.news_app.dto.ShareDto;
import uz.lee.news_app.service.ShareService;

@RestController
@RequestMapping("/api/shares")
public class ShareController {
    private final ShareService service;

    public ShareController(ShareService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody ShareDto dto) {
        return ResponseEntity.ok().build();
    }
}
