package uz.lee.news_app.post;

import uz.lee.news_app.attachment.Attachments;
import uz.lee.news_app.user.dao.UsersShortInfo;

import java.time.LocalDateTime;

/**
 * Projection for {@link Posts}
 */
public interface ShortPostsInfo {
    Long getId();

    String getTitle();
    LocalDateTime getCreatedAt();

    UsersShortInfo getWriter();

    Attachments getMainAttachment();
}