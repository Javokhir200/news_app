package uz.lee.news_app.post;

import java.time.LocalDateTime;
import java.util.List;


public interface PostProjection {
    Long getId();
    String getContent();
    LocalDateTime getCreatedAt();
    String getTitle();
    Long getWriterId();
    String getUsername();
    List<String> getTags();
    List<String> getAttachments();
}
