package uz.lee.news_app.dto;

import lombok.*;
import uz.lee.news_app.post.PostDto;
import uz.lee.news_app.user.UserDto;

/**
 * DTO for {@link uz.lee.news_app.domain.Archived}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArchivedDto {
    private Integer id;
    private UserDto user;
    private PostDto post;
}