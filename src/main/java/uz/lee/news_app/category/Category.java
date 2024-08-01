package uz.lee.news_app.category;

import jakarta.persistence.*;
import lombok.*;
import uz.lee.news_app.attachment.Attachments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Attachments attachment;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Category> children = new HashSet<>();
    private boolean parent = false;
}
