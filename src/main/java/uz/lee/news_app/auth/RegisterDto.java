package uz.lee.news_app.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String prePassword;
}
