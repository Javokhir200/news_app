package uz.lee.news_app.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO for {@link uz.lee.news_app.domain.Users}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private LocalDateTime createdAt;
}