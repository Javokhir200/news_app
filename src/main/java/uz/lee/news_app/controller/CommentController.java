package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.CommentsDto;
import uz.lee.news_app.service.CommentService;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody CommentsDto dto) {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().build();
    }
}
