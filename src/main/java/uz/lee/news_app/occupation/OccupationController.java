package uz.lee.news_app.occupation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occupation")
public class OccupationController {
    private final OccupationService service;

    public OccupationController(OccupationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OccupationDto dto) {
        String resp = service.addOccupation(dto);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllOccupations());
    }
}
