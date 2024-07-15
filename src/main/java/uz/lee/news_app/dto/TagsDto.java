package uz.lee.news_app.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link uz.lee.news_app.domain.Tags}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagsDto {
    private Integer id;
    private String name;
    private PostsDto posts;
}