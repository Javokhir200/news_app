package uz.lee.news_app.attachment;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final String BASE_URL = "";
    public AttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    public String saveAttachment(MultipartFile file,String fileName){

        String attachmentUrl = BASE_URL + UUID.randomUUID() + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];


        attachmentRepository.save(
                Attachments.builder()
                        .attachmentUrl(attachmentUrl)
                        .contentType(file.getContentType())
                        .fileName(fileName)
                        .size(file.getSize())
                        .build()
        );

        return attachmentUrl;
    }

    public Attachments getAttachmentByUrl(String attachmentUrl){
        return attachmentRepository.findByAttachmentUrl(attachmentUrl).orElseGet(() ->
             attachmentRepository.save(Attachments.builder().attachmentUrl(attachmentUrl).build())
        );
    }
}
