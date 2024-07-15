package uz.lee.news_app.dto;

import lombok.*;

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
    private UsersDto user;
    private PostsDto post;
}