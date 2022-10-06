package formacionBackend5.Login.domain.repository;


import formacionBackend5.Login.domain.service.UserP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserP, Integer> {

    List<UserP> findByName(String name);
    void deleteAll();

}
