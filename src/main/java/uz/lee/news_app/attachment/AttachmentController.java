package uz.lee.news_app.attachment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.lee.news_app.aop.CheckPermissions;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/save-attachment")
    public ResponseEntity<?> saveAttachment(@RequestParam MultipartFile file){
        return ResponseEntity.ok("attachmentUrl= " + attachmentService.saveAttachment(file));
    }
}
