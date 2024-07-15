package uz.lee.news_app.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link uz.lee.news_app.domain.Category}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    private AttachmentsDto attachment;
}