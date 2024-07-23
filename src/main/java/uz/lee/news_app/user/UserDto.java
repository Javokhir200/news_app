package uz.lee.news_app.user;

import lombok.*;

/**
 * DTO for {@link Users}
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
    private String attachmentUrl;
    private Long occupationId;
}