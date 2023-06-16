package daily.learn.auth.services;

import daily.learn.auth.models.Role;
import daily.learn.auth.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
/*
Use RequiredArgsConstructor to replace manual write constructor
 */
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

//    public RoleService(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
