package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.TagsDto;
import uz.lee.news_app.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody TagsDto dto) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
 }
