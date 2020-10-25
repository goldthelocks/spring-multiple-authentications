package ph.eraine.poc.multipleauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.eraine.poc.multipleauth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameIgnoreCase(String username);

}