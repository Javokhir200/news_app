package uz.lee.news_app.roles;

import org.springframework.stereotype.Service;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.custom_responses.exceptions.SourceAlreadyExistException;
import uz.lee.news_app.custom_responses.exceptions.SourceIsNotExistException;

import java.util.*;


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

    public ApiResponse editRoleById(Integer id, RoleDto roleDto) {
        Roles role = rolesRepository.findById(id).orElseThrow(() -> new SourceIsNotExistException("Role is not present id=" + id));
        if (roleDto.getName()!=null){
            role.setName(roleDto.getName());
        }
        if (roleDto.getPermissions() != null)
        {
            role.setPermissions(roleDto.getPermissions());
        }
        rolesRepository.save(role);

        return new ApiResponse("Role saved successfully id=" + id,true);
    }

    public ApiResponse deleteById(Integer id) {
        rolesRepository.deleteById(id);

        return new ApiResponse("Role deleted successfully id="+id,true);
    }
}
