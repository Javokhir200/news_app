package uz.lee.news_app.occupation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupationsRepository extends JpaRepository<Occupations, Long> {
    Boolean existsByNameIgnoreCase(String name);
}