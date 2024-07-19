package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().build();
    }
 }
