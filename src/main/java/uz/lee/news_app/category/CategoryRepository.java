package uz.lee.news_app.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Boolean existsByNameIgnoreCase(String name);
    List<Category> getCategoriesByParentIsTrue();
    Optional<Category> getCategoryById(Integer id);

    @Query("select c.parent from Category c where c.id = :id")
    Boolean isParent(@Param("id") Integer categoryId);
}