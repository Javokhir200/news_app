package uz.lee.news_app.attachment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachments,Long> {

    Optional<Attachments> findByAttachmentUrl(String attachmentUrl);
}
