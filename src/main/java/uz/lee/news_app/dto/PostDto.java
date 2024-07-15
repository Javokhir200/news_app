package uz.lee.news_app.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO for {@link uz.lee.news_app.domain.Posts}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String title;
    private AttachmentDto images;
    private String content;
    private LocalDateTime createdAt;
    private UserDto writer;
    private Sub_CategoryDto subCategory;
}