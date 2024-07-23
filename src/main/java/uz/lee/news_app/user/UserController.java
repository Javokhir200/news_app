package uz.lee.news_app.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.ApiResponse;
import uz.lee.news_app.exceptions.ForbiddenException;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable Long userId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/follow")
    public ResponseEntity<?> follow(@RequestParam("followerId") Long followerId,@RequestParam("ownerId") Long accountOwnerId){
        ApiResponse resp = userService.followUser(followerId, accountOwnerId);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @GetMapping("/unfollow")
    public ResponseEntity<?> unFollow(@RequestParam("followerId") Long followerId,@RequestParam("ownerId") Long accountOwnerId){
        ApiResponse resp = userService.unfollowUser(followerId, accountOwnerId);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @GetMapping("/get-followers-count/{id}")
    public ResponseEntity<?> getFollowersCountByUserId(@PathVariable Long id){
        long count = userService.countFollowers(id);
        return ResponseEntity.ok("followers= " + count);
    }

    @GetMapping("/get-followings-count/{id}")
    public ResponseEntity<?> getFollowingsCountByUserId(@PathVariable Long id){
        long count = userService.countFollowings(id);
        return ResponseEntity.ok("followings= " + count);
    }

    @PutMapping
    public ResponseEntity<?> editPersonalData(@RequestParam("username") String username,@RequestBody UserDto dto, Principal principal){
        String var = principal.getName();
        if (!var.equals(username)){
            throw new ForbiddenException("You do not have permission to access this resource");
        }
        ApiResponse resp = userService.editUser(username,dto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }
}
