package uz.lee.news_app.tag;

import lombok.*;

/**
 * DTO for {@link Tags}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDto {
    private Integer id;
    private String name;
}