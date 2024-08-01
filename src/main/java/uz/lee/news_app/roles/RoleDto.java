package uz.lee.news_app.roles;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
   private Integer id;
   private String name;
   private Set<Permission> permissions;
}