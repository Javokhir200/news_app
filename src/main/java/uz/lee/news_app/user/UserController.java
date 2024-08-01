package uz.lee.news_app.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.aop.CheckPermissions;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.custom_responses.exceptions.ForbiddenException;
import uz.lee.news_app.user.dto.UserResponseDto;
import uz.lee.news_app.user.dto.UserRequestDto;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<?> getById(@RequestParam("username") String username,Principal principal) {
        UserResponseDto userResponseDto;
        if (checkPermission(username,principal)) {
            userResponseDto =  userService.getDataByOwner(username);
        }else{
            userResponseDto =  userService.getDataByOther(username);
        }

        return ResponseEntity.ok(userResponseDto);
    }

    @CheckPermissions(permission = "CAN_FOLLOW_TO_USER")
    @GetMapping("/follow")
    public ResponseEntity<?> follow(@RequestParam("username") String followingUsername, Principal principal){
        if (checkPermission(followingUsername, principal)) {
            throw new ForbiddenException("You can't follow to yourself!");
        }
        ApiResponse resp = userService.followToUser(principal.getName(), followingUsername);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @CheckPermissions(permission = "CAN_UNFOLLOW_FROM_USER")
    @GetMapping("/unfollow")
    public ResponseEntity<?> unFollow(@RequestParam("username") String accountOwnerUsername,Principal principal){
        if (checkPermission(accountOwnerUsername, principal)) {
            throw new ForbiddenException("You can't unfollow from yourself!");
        }
        ApiResponse resp = userService.unfollowUser(principal.getName(), accountOwnerUsername);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @CheckPermissions(permission = "CAN_GET_FOLLOWERS_COUNT_BY_USERNAME")
    @GetMapping("/{username}/followers")
    public ResponseEntity<?> getFollowersCountByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok("followers= " + userService.countFollowers(username));
    }

    @CheckPermissions(permission = "CAN_GET_FOLLOWINGS_COUNT_BY_USERNAME")
    @GetMapping("/{username}/followings")
    public ResponseEntity<?> getFollowingsCountByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok("followings= " + userService.countFollowings(username));
    }

    @CheckPermissions(permission = "CAN_EDIT_OWN_DATA")
    @PutMapping
    public ResponseEntity<?> editPersonalData(@RequestParam("username") String username, @RequestBody UserRequestDto dto, Principal principal){
        if (!checkPermission(username, principal)) {
            throw new ForbiddenException("You don't have access to this resource !!!");
        }
        ApiResponse resp = userService.editUser(username,dto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @CheckPermissions(permission = "CAN_DELETE_OWN_ACCOUNT")
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam("password") String password,@RequestParam("username") String username,Principal principal){
        if (checkPermission(username,principal)){
            return ResponseEntity.ok(userService.deleteByUsernameAndPassword(username,password));
        }
        throw new ForbiddenException("You don't have permission for that action !");
    }
    public boolean checkPermission(String username,Principal principal){
        return principal.getName().equals(username);
    }
}
