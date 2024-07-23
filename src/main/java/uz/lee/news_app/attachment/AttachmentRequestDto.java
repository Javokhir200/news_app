package uz.lee.news_app.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentRequestDto {

    private String fileName;
    private String fileUrl;
}
