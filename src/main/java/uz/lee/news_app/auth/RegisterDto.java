package uz.lee.news_app.auth;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.lee.news_app.attachment.Attachments;

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
