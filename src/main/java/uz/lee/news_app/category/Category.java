package uz.lee.news_app.category;

import jakarta.persistence.*;
import lombok.*;
import uz.lee.news_app.attachment.Attachments;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToOne
    private Attachments attachment;
    @OneToMany
    private List<Category> children = new ArrayList<>();
    private boolean parent = false;
}
