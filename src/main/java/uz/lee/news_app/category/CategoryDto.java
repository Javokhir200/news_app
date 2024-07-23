package uz.lee.news_app.category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    private String attachmentUrl;
}