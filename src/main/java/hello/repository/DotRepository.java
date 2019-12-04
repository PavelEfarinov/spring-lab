package hello.repository;

import hello.domain.Dot;
import hello.domain.DotDTO;
import hello.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DotRepository extends JpaRepository<Dot, Long> {
    List<Dot> findAllByOwner(User byUsername);
    void deleteAllByOwner(User byUsername);
}
