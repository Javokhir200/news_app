package uz.lee.news_app.dto;

import lombok.*;

/**
 * DTO for {@link uz.lee.news_app.domain.Permissions}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionsDto {
    private Integer id;
    private String name;
    private RolesDto role;
}