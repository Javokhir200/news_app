package uz.lee.news_app.tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.aop.CheckPermissions;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService service;
    public TagController(TagService service) {
        this.service = service;
    }
    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<Tags> tags = service.getAll();
        return ResponseEntity.ok(tags);
    }
 }
