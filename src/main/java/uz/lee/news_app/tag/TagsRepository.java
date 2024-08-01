package uz.lee.news_app.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface TagsRepository extends JpaRepository<Tags, Long> {

    Set<Tags> getTagsByName(String name);
}