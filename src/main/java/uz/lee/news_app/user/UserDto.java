package uz.lee.news_app.user;

import lombok.*;
import uz.lee.news_app.occupation.OccupationDto;

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