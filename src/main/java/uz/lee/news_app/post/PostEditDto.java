package uz.lee.news_app.post;

import lombok.*;
import uz.lee.news_app.attachment.dto.AttachmentDto;

import java.util.List;
import java.util.Set;

/**
 * DTO for {@link Posts}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostEditDto {
    private Long id;
    private String title;

    private String content;
    private List<AttachmentDto> attachments;
    private Set<Long> tagIds;
    private Long writerId;
}