package uz.lee.news_app.auth;


import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.lee.news_app.dto.ApiResponse;
import uz.lee.news_app.email.EmailService;
import uz.lee.news_app.provider.JwtProvider;
import uz.lee.news_app.user.Users;
import uz.lee.news_app.user.UsersRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsersRepository usersRepository, EmailService emailService, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.emailService = emailService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    public ApiResponse register(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponse("Password and prepassword didn't matched",false);
        }
        if (usersRepository.existsByEmailIgnoreCase(registerDto.getEmail())){
            return new ApiResponse("This email is already occupied",false);
        }
        if (usersRepository.existsByUsernameIgnoreCase(registerDto.getUsername())){
            return new ApiResponse("This username is already occupied",false);
        }

        String url = emailService.sendHtmlEmail(registerDto.getEmail());

        Users users = new Users();
        users.setUsername(registerDto.getUsername());
        users.setAccountActivationLink(url);
        users.setFullName(registerDto.getFullName());
        users.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        usersRepository.save(users);

        return new ApiResponse("Check your email we have sent link to activate your account",true);
    }

    public ApiResponse verifyEmail(String activationLink) {
        Optional<Users> byAccountActivationLink = usersRepository.findByAccountActivationLink(activationLink);
        if (byAccountActivationLink.isPresent()){
            Users users = byAccountActivationLink.get();
            users.setEnabled(true);
            usersRepository.save(users);
            return new ApiResponse("Your account enabled!",true);
        }

        return new ApiResponse("Please try again",false);
    }

    public ApiResponse login(LoginDto loginDto) {
        Users users = usersRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword()).orElseThrow(() -> new UsernameNotFoundException("Username is not found!"));
        String token = jwtProvider.generateToken(users);
        return new ApiResponse("Token=" + token,true);
    }
}
