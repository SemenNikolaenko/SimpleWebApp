package sem.nik.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sem.nik.demo.entiities.User;

@Repository
public interface UserRepository  extends CrudRepository<User,Long> {
    User findOneByUsername(String username);
}
