package uz.lee.news_app.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link uz.lee.news_app.domain.Sub_Category}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sub_CategoryDto {
    private Integer id;
    private String name;
    private CategoryDto superCategory;
}