package uz.lee.news_app.archived;

import lombok.*;

/**
 * DTO for {@link Archived}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArchivedDto {
    private Long userId;
    private Long postId;
}