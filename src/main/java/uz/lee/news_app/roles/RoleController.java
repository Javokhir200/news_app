package uz.lee.news_app.roles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.aop.CheckPermissions;
import uz.lee.news_app.custom_responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        List<Roles> roles = service.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @CheckPermissions(permission = "CAN_GET_ROLE_BY_ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Integer id){
        Roles roles = service.getRoleById(id);
        return ResponseEntity.ok(roles);
    }

    @CheckPermissions(permission = "CAN_ADD_ROLE")
    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody RoleDto dto){
        String resp = service.addRole(dto);
        return ResponseEntity.ok(resp);
    }

    @CheckPermissions(permission = "CAN_EDIT_ROLE_BY_ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> editRoleById(@PathVariable Integer id,@RequestBody RoleDto roleDto){
        ApiResponse resp = service.editRoleById(id,roleDto);
        return ResponseEntity.ok(resp);
    }

    @CheckPermissions(permission = "CAN_DELETE_ROLE_BY_ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        ApiResponse resp = service.deleteById(id);
        return ResponseEntity.ok(resp);
    }
}
