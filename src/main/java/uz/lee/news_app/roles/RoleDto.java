package uz.lee.news_app.roles;

import lombok.*;
import uz.lee.news_app.roles.enums.Permission;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
   private Integer id;
   private String name;
   private List<Permission> permissions;
}