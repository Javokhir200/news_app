package uz.lee.news_app.domain;

import jakarta.persistence.*;
import lombok.*;
import uz.lee.news_app.post.Posts;
import uz.lee.news_app.user.Users;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    private Posts post;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users fromUser;
    private String toUser;
    private String rate;
}
