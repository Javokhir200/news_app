package uz.lee.news_app.dto;

import lombok.*;

/**
 * DTO for {@link uz.lee.news_app.domain.Likes}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDto {
    private Integer id;
    private PostDto posts;
    private UserDto user;
}