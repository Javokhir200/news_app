package uz.lee.news_app.dto;

import lombok.*;

/**
 * DTO for {@link uz.lee.news_app.domain.Comments}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsDto {
    private Integer id;
    private String text;
    private PostsDto post;
    private UsersDto from;
    private String to;
}