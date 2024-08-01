package uz.lee.news_app.user.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.lee.news_app.user.Users;

public interface FollowsRepository extends JpaRepository<Follows, Long> {

    Boolean deleteByFollowerUsernameAndFollowingUsername(String follower_username, String following_username);
    Long countAllByFollowerUsername(String follower_username);
    Long countAllByFollowingUsername(String following_username);
}