package formacionBackend5.Login.domain.service;


import formacionBackend5.Login.domain.UserP;
import formacionBackend5.Login.exceptions.EntityNotFoundException;
import formacionBackend5.Login.exceptions.UnprocessableEntityException;
import formacionBackend5.Login.domain.dto.UserPInputDTO;
import formacionBackend5.Login.domain.dto.UserPOutputDTO;
import formacionBackend5.Login.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserPServiceImpl implements UserPService {


    @Autowired
    private UserRepository userRepository;


    /*
     *
     * Recibimos el inputDTO
     * creamos un UserP
     * Llamos al metodo de UserP pasandole el inputDTO
     * lo guardamos en el repositorio
     * retornamos el Output de UserP
     * */
    @Override
    public UserPOutputDTO createUser(UserPInputDTO userPInputDTO) throws UnprocessableEntityException {


        if ((userPInputDTO.getUser() == null) || (userPInputDTO.getUser().equals(""))) {
            throw new UnprocessableEntityException("UserP field can not be null", 422);
        }
        if (userPInputDTO.getUser().length() > 10) {
            throw new UnprocessableEntityException("UserP field length is not > 10", 422);
        }

        UserP userP = new UserP();

        userP.createUser(userPInputDTO);
        userRepository.save(userP);
        return new UserPOutputDTO(userP);

    }


    /*
     *
     * Creamos user y le asignamos el user del repositorio con el id pasado por parametro(ya tenemos aqui el user a actualizar)
     * Lo guardamos con los datos recibidos
     * y lo retornamos
     *
     * */
    @Override
    public UserPOutputDTO updateUser(UserPInputDTO userPInputDTO, Integer id) throws EntityNotFoundException {

        Optional<UserP> userOpt = userRepository.findById(id);

        if(userOpt.isEmpty())
            throw new EntityNotFoundException("The userP does no exist", 404);

        if ((userPInputDTO.getUser() == null) || (userPInputDTO.getUser().equals("")))
            throw new UnprocessableEntityException("UserP field can not be null", 422);

        UserP userP = userOpt.get();

        userP.setIdUser(id);
        userP.createUser(userPInputDTO);
        userRepository.save(userP);

        return new UserPOutputDTO(userP);
    }


    /*
     * Recibimos el id
     * Buscamos en el repositorio de UserP un usuario con el mismo id que el psado como parametro
     * lo retornamos
     * */
    @Override
    public UserPOutputDTO getUser(Integer id) throws EntityNotFoundException {

        //Obtengo el usuario por si id
        UserP userP = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserP not found",404));

        return new UserPOutputDTO(userP);
    }


    @Override
    public void deleteUser(Integer id) {


        //Obtengo el usuario por si id
        Optional<UserP> userOpt = userRepository.findById(id);

        if(userOpt.isEmpty())
            throw new EntityNotFoundException("The user does no exist", 404);

        userRepository.deleteById(id);
    }



    @Override
    public List<UserP> findByName(String name) {

        return userRepository.findByName(name);
    }

    @Override
    public List<UserP> findAll() {
        return userRepository.findAll();
    }


    @Override
    public Optional<UserP> findBypersonalEmail(String personalEmail) {
        return userRepository.findBypersonalEmail(personalEmail);
    }

}
