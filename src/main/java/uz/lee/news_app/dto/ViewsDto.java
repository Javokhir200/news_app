package uz.lee.news_app.dto;

import lombok.*;

/**
 * DTO for {@link uz.lee.news_app.domain.Views}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewsDto {
    private Integer id;
    private PostsDto post;
    private int count;
}