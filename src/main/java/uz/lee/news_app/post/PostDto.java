package uz.lee.news_app.post;

import lombok.*;
import uz.lee.news_app.attachment.AttachmentRequestDto;
import uz.lee.news_app.attachment.AttachmentResponseDto;
import uz.lee.news_app.tag.TagDto;
import uz.lee.news_app.user.UserDto;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<AttachmentRequestDto> attachments;
    private List<TagDto> tags;
    private Long writerId;
}