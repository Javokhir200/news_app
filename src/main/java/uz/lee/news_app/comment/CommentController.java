package uz.lee.news_app.comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentDto dto) {
        return ResponseEntity.ok(service.saveComment(dto));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(service.getCommentsByPostId(postId));
    }
}
