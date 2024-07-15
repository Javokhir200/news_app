package uz.lee.news_app.dto;

import lombok.*;

/**
 * DTO for {@link uz.lee.news_app.domain.Attachments}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentDto {
    private Integer id;
    private String originalName;
    private String submittedName;
}