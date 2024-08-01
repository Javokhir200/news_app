package uz.lee.news_app.user.dto;

import lombok.*;
import uz.lee.news_app.attachment.Attachments;
import uz.lee.news_app.attachment.dto.AttachmentDto;
import uz.lee.news_app.user.Users;

/**
 * DTO for {@link Users}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private Attachments attachments;
    private String occupation;

    private Long followersCount;
    private Long followingsCount;
}