package daily.learn.auth.repositories;

import daily.learn.auth.models.Role;
import daily.learn.auth.models.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role,Long> {

    Page<Role> findAll(Pageable pageable);

    Role findByName(String name);

}
