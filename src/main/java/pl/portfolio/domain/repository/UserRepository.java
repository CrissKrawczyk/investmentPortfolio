package pl.portfolio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.portfolio.domain.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByLogin(String login);
}
