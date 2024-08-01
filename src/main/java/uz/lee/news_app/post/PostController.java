package uz.lee.news_app.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.custom_responses.exceptions.ForbiddenException;

import java.security.Principal;
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
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/get-by-tag-name")
    public ResponseEntity<?> getByTagName(@RequestParam("tagName") String tag){
        List<ShortPostsInfo> resp = service.getByTagName(tag);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/get-by-username/{username}")
    public ResponseEntity<?> getByWriterId(@PathVariable("username") String username) {
        return ResponseEntity.ok(service.getByWriterUsername(username));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> editPostById(@PathVariable Long postId,@RequestBody PostEditDto postEditDto, Principal principal) {
        System.out.println(principal.getName());
        return ResponseEntity.ok(service.editByPostId(postId,postEditDto,principal));
    }

    @DeleteMapping("/{username}/posts/{postId}")
    public ResponseEntity<?> deletePostById(@PathVariable Long postId, @PathVariable String username, Principal principal) {
        if (!principal.getName().equals(username)){
            throw new ForbiddenException("You don't have permission to edit this data!");
        }
        return ResponseEntity.ok(service.deletePostById(postId));
    }
}
