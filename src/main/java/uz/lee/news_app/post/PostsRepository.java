package uz.lee.news_app.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    Optional<ShortPostsInfo> findPostsById(Long id);

    List<ShortPostsInfo> findAllByTagsNameContains(String tags_name);

    List<ShortPostsInfo> findAllByWriterUsername(String writer_username);

    @Query("select p.writer.username from Posts p where p.id =: postId")
    String getWriterUsernameByPostId(@Param("postId") Long postId);
}