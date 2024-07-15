package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.PermissionsDto;
import uz.lee.news_app.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody PermissionsDto dto) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
}
