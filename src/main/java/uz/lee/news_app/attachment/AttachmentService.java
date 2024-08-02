package uz.lee.news_app.attachment;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final String BASE_PACKAGE = "src/main/resources/BASE/";
    public AttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @SneakyThrows
    public String saveAttachment(MultipartFile file){
        String givenName = UUID.randomUUID() + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        String attachmentUrl = BASE_PACKAGE + givenName;

        attachmentRepository.save(
                Attachments.builder()
                        .attachmentUrl(attachmentUrl)
                        .contentType(file.getContentType())
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
