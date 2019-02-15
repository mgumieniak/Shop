package shop.data;

import org.springframework.data.repository.CrudRepository;
import shop.domens.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
