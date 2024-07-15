package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.FollowsDto;
import uz.lee.news_app.service.FollowService;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService service;

    public FollowController(FollowService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(FollowsDto dto) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
}
