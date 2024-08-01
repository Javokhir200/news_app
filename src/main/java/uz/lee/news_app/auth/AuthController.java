package uz.lee.news_app.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.aop.CheckPermissions;
import uz.lee.news_app.custom_responses.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CheckPermissions(permission = "CAN_REGISTER")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        ApiResponse resp = authService.register(registerDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @CheckPermissions(permission = "CAN_VERIFY_ACCOUNT")
    @GetMapping("/verify/{activationLink}")
    public ResponseEntity<?> verification(@PathVariable String activationLink){
        ApiResponse resp = authService.verifyEmail(activationLink);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @CheckPermissions(permission = "CAN_LOGIN")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse resp = authService.login(loginDto);
        return ResponseEntity.ok(resp);
    }
}
