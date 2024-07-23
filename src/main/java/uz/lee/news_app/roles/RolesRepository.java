package uz.lee.news_app.roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Boolean existsByNameIgnoreCase(String name);
}