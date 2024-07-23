package uz.lee.news_app.attachment;

import lombok.*;

/**
 * DTO for {@link Attachments}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentResponseDto {

    private String fileName;
    private String attachmentUrl;

}