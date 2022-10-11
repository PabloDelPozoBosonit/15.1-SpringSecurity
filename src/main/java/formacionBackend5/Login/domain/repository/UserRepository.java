package formacionBackend5.Login.domain.repository;


import formacionBackend5.Login.domain.UserP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserP, Integer> {

    List<UserP> findByName(String name);
    void deleteAll();

    Optional<UserP> findBypersonalEmail(String personalEmail);

}
