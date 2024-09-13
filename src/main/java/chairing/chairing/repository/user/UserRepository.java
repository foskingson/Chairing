package chairing.chairing.repository.user;

import org.springframework.stereotype.Repository;

import chairing.chairing.domain.user.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByusername(String username);
}