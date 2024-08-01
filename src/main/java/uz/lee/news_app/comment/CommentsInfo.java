package uz.lee.news_app.comment;

import uz.lee.news_app.user.dao.UsersShortInfo;

import java.time.LocalDateTime;

/**
 * Projection for {@link Comments}
 */
public interface CommentsInfo {
    Long getId();

    String getText();

    Double getRate();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    PostsInfo getPost();

    UsersShortInfo getFromUser();

    /**
     * Projection for {@link uz.lee.news_app.post.Posts}
     */
    interface PostsInfo {
        Long getId();
    }

    /**
     * Projection for {@link uz.lee.news_app.user.Users}
     */
    UsersShortInfo getToUser();
}