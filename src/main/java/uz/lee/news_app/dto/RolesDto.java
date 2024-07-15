package uz.lee.news_app.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link uz.lee.news_app.domain.Roles}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesDto {
   private Integer id;
   private String name;
}