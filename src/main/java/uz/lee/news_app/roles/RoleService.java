package uz.lee.news_app.roles;

import org.springframework.stereotype.Service;
import uz.lee.news_app.exceptions.SourceAlreadyExistException;
import uz.lee.news_app.exceptions.SourceIsNotExistException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RolesRepository rolesRepository;

    public RoleService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public String addRole(RoleDto dto) {
        Boolean b = rolesRepository.existsByNameIgnoreCase(dto.getName());
        if (b){
            throw new SourceAlreadyExistException("Role already exist with name= " + dto.getName());
        }

        rolesRepository.save(Roles.builder().name(dto.getName()).permissions(dto.getPermissions()).build());

        return "Role saved with name= " + dto.getName();
    }

    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    public Roles getRoleById(Integer id) {
        Optional<Roles> byId = rolesRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }

        throw new SourceIsNotExistException("Role is not exist with id= " + id);
    }
}
