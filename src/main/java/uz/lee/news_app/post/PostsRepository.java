package uz.lee.news_app.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p.id AS id, p.content AS content, p.createdAt AS createdAt, p.title AS title, " +
            "p.writer.id AS writerId, p.writer.username AS username, " +
            "t.name AS tags, a.attachmentUrl AS attachments " +
            "FROM Posts p " +
            "JOIN p.tags t " +
            "LEFT JOIN p.attachments a " +
            "WHERE t.name = :tagName " +
            "ORDER BY p.id ASC")
    List<PostProjection> getAllByTagName(@Param("tagName") String tagName);

    @Query("select p.id as id,p.title as title,p.createdAt as createdAt " +
            "from Posts p where p.writer.id = :writerId")
    List<PostProjection> getAllByWriteId(@Param("writerId") Long writerId);
}