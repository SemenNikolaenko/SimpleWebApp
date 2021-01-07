package sem.nik.demo.services;

import org.springframework.stereotype.Service;
import sem.nik.demo.entiities.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService  extends UserDetailsService{
    User findOneByUsername(String username);
}
