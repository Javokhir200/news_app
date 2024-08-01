package uz.lee.news_app.user.dao;

import uz.lee.news_app.attachment.AttachmentsInfo;
import uz.lee.news_app.user.Users;

/**
 * Projection for {@link Users}
 */
public interface UsersShortInfo {
    Long getId();
    String getUsername();
    AttachmentsInfo getAttachments();
}