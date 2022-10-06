package formacionBackend5.Login.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //El array List seria el tipo de role del User
        //La contraseña al no estar puesta en ningun codificador, hay que escribirle {noop}
        return new User("Pablo", "{noop}12345", new ArrayList<>());

    }
}
