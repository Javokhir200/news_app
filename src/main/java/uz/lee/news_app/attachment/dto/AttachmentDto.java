package uz.lee.news_app.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentDto {
    private String fileName;
    private String AttachmentUrl;
}
