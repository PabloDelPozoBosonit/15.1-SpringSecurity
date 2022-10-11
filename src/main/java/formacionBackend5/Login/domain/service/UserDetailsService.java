package formacionBackend5.Login.domain.service;

import formacionBackend5.Login.domain.UserP;
import formacionBackend5.Login.domain.repository.UserRepository;

import formacionBackend5.Login.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserP> userOpt = userRepository.findBypersonalEmail(email);

        if(userOpt.isEmpty())
            throw new EntityNotFoundException("The user does no exist", 404);


        UserP userp = userOpt.get();

        log.info("Nomrbe del usuario: " + userp.getName());
        log.info("Apellido del usuario: " + userp.getSurname());
        log.info("Email del usuario: " + userp.getPersonalEmail());

        String password = "{noop}" + userp.getPassword();

        //El array List seria el tipo de role del User
        //La contraseña al no estar puesta en ningun codificador, hay que escribirle {noop}
        return new User(userp.getPersonalEmail(), password, new ArrayList<>());

    }
}
