package uz.lee.news_app.post;

import lombok.*;
import uz.lee.news_app.attachment.dto.AttachmentDto;
import uz.lee.news_app.tag.Tags;

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
public class PostDto {
    private Long id;
    private String title;

    private String content;
    private List<AttachmentDto> attachments;
    private Set<Tags> tags;
    private Long writerId;
}