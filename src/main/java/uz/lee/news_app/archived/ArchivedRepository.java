package uz.lee.news_app.archived;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivedRepository extends JpaRepository<Archived, Long> {

    List<ArchivedInfo> findAllArchivesByUserUsername(String username);
}