package uz.lee.news_app.dto;

import lombok.*;

/**
 * DTO for {@link uz.lee.news_app.domain.Follows}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowsDto {
    private Long id;
    private UsersDto follows;
    private UsersDto writer;
}