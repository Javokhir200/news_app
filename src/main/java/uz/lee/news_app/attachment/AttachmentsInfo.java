package uz.lee.news_app.attachment;

import uz.lee.news_app.attachment.Attachments;

/**
 * Projection for {@link Attachments}
 */
public interface AttachmentsInfo {
    Long getId();

    String getFileName();

    String getAttachmentUrl();

    String getContentType();

    long getSize();
}