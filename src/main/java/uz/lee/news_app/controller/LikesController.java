package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.LikeDto;
import uz.lee.news_app.service.LikeService;

@RestController
@RequestMapping("/api/likes")
public class LikesController {
    private final LikeService service;

    public LikesController(LikeService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(LikeDto dto) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
}
