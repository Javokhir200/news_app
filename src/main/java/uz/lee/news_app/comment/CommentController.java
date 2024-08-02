package uz.lee.news_app.comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.aop.CheckPermissions;

import java.security.Principal;


@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentDto dto, @RequestParam("postId") Long postId, @RequestParam("toUserId") Long toUserId, Principal principal) {
        return ResponseEntity.ok(service.saveComment(dto,postId,toUserId,principal.getName()));
    }


    @GetMapping("/{postId}")
    public ResponseEntity<?> getByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(service.getCommentsByPostId(postId));
    }
}
