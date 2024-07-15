package uz.lee.news_app.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link uz.lee.news_app.domain.Attachments}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentsDto {
    private Integer id;
    private String originalName;
    private String submittedName;
}