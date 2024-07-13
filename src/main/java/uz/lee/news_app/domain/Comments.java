package uz.lee.news_app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Users from;
    private String to;
    private String rate;
}
