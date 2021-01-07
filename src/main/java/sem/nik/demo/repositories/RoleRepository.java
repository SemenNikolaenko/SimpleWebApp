package sem.nik.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sem.nik.demo.entiities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String name);
}
