package uz.lee.news_app.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody PostDto dto) {
        ApiResponse resp = service.createPost(dto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Posts resp = service.getById(id);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<?> getByTagName(@RequestParam("tagName") String tag){
        List<PostProjection> resp = service.getByTagName(tag);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/get-by-writer/{id}")
    public ResponseEntity<?> getByWriterId(@PathVariable("id") Long id) {
        List<PostProjection> resp = service.getByWriterId(id);
        return ResponseEntity.ok(resp);
    }
}
