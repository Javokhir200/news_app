package uz.lee.news_app.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link uz.lee.news_app.domain.Occupations}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OccupationsDto {
    private Integer id;
    private String name;
    private String color;
}