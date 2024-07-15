package uz.lee.news_app.dto;

import lombok.*;

/**
 * DTO for {@link uz.lee.news_app.domain.Sections}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionDto {
    private Integer id;
    private String name;
    private String text;
    private AttachmentDto attachments;
}