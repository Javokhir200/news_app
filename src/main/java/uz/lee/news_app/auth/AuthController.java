package uz.lee.news_app.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.dto.ApiResponse;
import uz.lee.news_app.user.Users;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        ApiResponse resp = authService.register(registerDto);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @GetMapping("/verify/{activationLink}")
    public ResponseEntity<?> verification(@PathVariable String activationLink){
        ApiResponse resp = authService.verifyEmail(activationLink);
        return ResponseEntity.status(resp.isSuccess()?200:400).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse resp = authService.login(loginDto);
        return ResponseEntity.ok(resp);
    }
}
