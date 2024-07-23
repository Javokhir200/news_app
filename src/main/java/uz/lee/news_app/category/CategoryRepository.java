package uz.lee.news_app.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsByNameIgnoreCase(String name);
    List<Category> getCategoriesByParentIsTrue();
    Optional<Category> getCategoryById(Integer id);
}