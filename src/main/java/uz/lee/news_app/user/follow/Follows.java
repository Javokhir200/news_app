package uz.lee.news_app.user.follow;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.lee.news_app.user.Users;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Follows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users follower;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users following;
}
