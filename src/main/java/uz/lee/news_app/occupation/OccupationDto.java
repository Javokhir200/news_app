package uz.lee.news_app.occupation;

import lombok.*;

/**
 * DTO for {@link Occupations}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OccupationDto {
    private int id;
    private String name;
}