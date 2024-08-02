package uz.lee.news_app.user.dto;

import lombok.*;
import uz.lee.news_app.user.Users;

/**
 * DTO for {@link Users}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String attachmentUrl;
    private Long occupationId;
    private Long followersCount;
    private Long followingsCount;
}