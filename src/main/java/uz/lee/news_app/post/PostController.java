package uz.lee.news_app.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.aop.CheckPermissions;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.custom_responses.exceptions.ForbiddenException;
import uz.lee.news_app.tag.TagsRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;
    private final TagsRepository tagsRepository;

    public PostController(PostService service,
                          TagsRepository tagsRepository) {
        this.service = service;
        this.tagsRepository = tagsRepository;
    }

    @CheckPermissions(permission = "CAN_ADD_POST")
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody PostDto dto) {
        ApiResponse resp = service.createPost(dto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @CheckPermissions(permission = "CAN_GET_POST_BY_ID")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @CheckPermissions(permission = "CAN_GET_POST_BY_TAG_NAME")
    @GetMapping
    public ResponseEntity<?> getByTagName(@RequestParam("tagName") String tag){
        List<ShortPostsInfo> resp = service.getByTagName(tag);
        return ResponseEntity.ok(resp);
    }

    @CheckPermissions(permission = "CAN_GET_POST_BY_USERNAME")
    @GetMapping("/get-by-username/{username}")
    public ResponseEntity<?> getByWriterId(@PathVariable("username") String username) {
        return ResponseEntity.ok(service.getByWriterUsername(username));
    }

    @CheckPermissions(permission = "CAN_EDIT_OWN_POST_BY_ID")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> editPostById(@PathVariable Long postId,@RequestBody PostEditDto postEditDto, Principal principal) {
        System.out.println(principal.getName());
        return ResponseEntity.ok(service.editByPostId(postId,postEditDto,principal));
    }

    @CheckPermissions(permission = "CAN_DELETE_OWN_POST_BY_ID")
    @DeleteMapping("/{username}/posts/{postId}")
    public ResponseEntity<?> deletePostById(@PathVariable Long postId, @PathVariable String username, Principal principal) {
        if (!principal.getName().equals(username)){
            throw new ForbiddenException("You don't have permission to edit this data!");
        }
        return ResponseEntity.ok(service.deletePostById(postId));
    }
}
