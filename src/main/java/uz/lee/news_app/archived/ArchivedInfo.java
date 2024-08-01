package uz.lee.news_app.archived;

import uz.lee.news_app.post.ShortPostsInfo;
import uz.lee.news_app.user.dao.UsersShortInfo;

/**
 * Projection for {@link Archived}
 */
public interface ArchivedInfo {
    Long getId();
    UsersShortInfo getUser();
    ShortPostsInfo getPost();
}