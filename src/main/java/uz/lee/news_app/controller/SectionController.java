package uz.lee.news_app.controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.SectionsDto;
import uz.lee.news_app.service.SectionService;

@RestController
@RequestMapping("/api/sections")
public class SectionController {
    private final SectionService service;

    public SectionController(SectionService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody SectionsDto dto) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
}