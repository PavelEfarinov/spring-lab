package hello.repository;

import hello.domain.User;
import hello.domain.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    int countByUsername(String login);

    User findByUsernameAndPassword(String login, String password);

    User findByUsername(String username);
}