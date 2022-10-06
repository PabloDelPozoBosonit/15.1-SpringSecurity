package formacionBackend5.Login.domain.service;



import formacionBackend5.Login.domain.dto.UserPInputDTO;
import formacionBackend5.Login.domain.dto.UserPOutputDTO;

import java.util.List;

public interface UserPService {

    UserPOutputDTO createUser(UserPInputDTO userPInputDTO) throws Exception;
    UserPOutputDTO updateUser(UserPInputDTO userPInputDTO, Integer id) throws Exception;
    UserPOutputDTO getUser(Integer id) throws Exception;
    void deleteUser(Integer id);

    List<UserP> findByName(String name);

    List<UserP> findAll();

}
