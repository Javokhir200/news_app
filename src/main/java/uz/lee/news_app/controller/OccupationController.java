package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.OccupationsDto;
import uz.lee.news_app.service.OccupationService;

@RestController
@RequestMapping("/api/occupations")
public class OccupationController {
    private final OccupationService service;

    public OccupationController(OccupationService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody OccupationsDto dto) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
}
