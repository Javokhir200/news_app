package uz.lee.news_app.roles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody RoleDto dto){
        String resp = service.addRole(dto);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<?> getAllRoles(){
        List<Roles> roles = service.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Integer id){
        Roles roles = service.getRoleById(id);
        return ResponseEntity.ok(roles);
    }

    /*
      updatemapping and deletemapping
     */
}
