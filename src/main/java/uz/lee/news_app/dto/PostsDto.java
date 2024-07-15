package uz.lee.news_app.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link uz.lee.news_app.domain.Posts}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsDto {
    private Long id;
    private String title;
    private AttachmentsDto images;
    private String content;
    private LocalDateTime createdAt;
    private UsersDto writer;
    private Sub_CategoryDto subCategory;
}