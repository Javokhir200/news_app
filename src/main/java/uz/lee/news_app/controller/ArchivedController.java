package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.ArchivedDto;
import uz.lee.news_app.service.ArchiveService;

@RestController
@RequestMapping("/api/archived")
public class ArchivedController {
    private final ArchiveService service;

    public ArchivedController(ArchiveService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody ArchivedDto dto) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
}
