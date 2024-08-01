package uz.lee.news_app.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import uz.lee.news_app.attachment.Attachments;
import uz.lee.news_app.user.Users;
import uz.lee.news_app.tag.Tags;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Attachments> attachments;
    private String content;
    @OneToOne
    private Attachments mainAttachment;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private Users writer;
    @OneToMany(cascade = {CascadeType.MERGE})
    private Set<Tags> tags;
}
