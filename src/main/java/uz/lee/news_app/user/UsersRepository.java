package uz.lee.news_app.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Boolean existsByUsernameIgnoreCase(String username);
    Boolean existsByEmailIgnoreCase(String email);

    Optional<Users> findByAccountActivationLink(String accountActivationLink);

    Optional<Users> findByUsername(String username);
    Optional<Users> findByUsernameAndPassword(String username, String password);
    Boolean deleteByUsernameAndPassword(String username, String password);
}