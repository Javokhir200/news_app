package uz.lee.news_app.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link uz.lee.news_app.domain.Likes}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikesDto {
    private Integer id;
    private PostsDto posts;
    private UsersDto user;
}