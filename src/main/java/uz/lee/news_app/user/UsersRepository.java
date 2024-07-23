package uz.lee.news_app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Boolean existsByUsernameIgnoreCase(String username);
    Boolean existsByEmailIgnoreCase(String email);

    Optional<Users> findByAccountActivationLink(String accountActivationLink);

    Optional<Users> findByUsername(String username);
    Optional<Users> findByUsernameAndPassword(String username, String password);

    @Query("SELECT COUNT(f) FROM Users u JOIN u.followers f WHERE u.id = :id")
    long countFollowersById(@Param("id") Long id);

    @Query("SELECT COUNT(f) FROM Users u JOIN u.followings f WHERE u.id = :id")
    long countFollowingsByById(@Param("id") Long id);
}