package formacionBackend5.Login.web.controller;

import formacionBackend5.Login.domain.service.UserDetailsService;
import formacionBackend5.Login.domain.dto.AuthenticationRequest;
import formacionBackend5.Login.domain.dto.AuthenticationResponse;
import formacionBackend5.Login.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //Inyectamos el gestor de autenticacion de spring
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;


    //Este metodo, respondera con un jwt, cuando alguien inicie sesion
    //Para eso, creamos un ResponseEntity del OutputDTO de la autenticacion
    //Recibimos un AuthenticationRequest(inputDTO)

    //Además, en web.config.SecurityConfig, añadimos este metodo para que puedan acceder todos los usuarios de la app
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>createToken(@RequestBody AuthenticationRequest request) {

       try {
           //Una vez esto, le decimos al gestor de autenticacion de spring, que verifique si el usuario y la contraseña son correctos
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
           //Ahora obtenemos los detalles del usuario desde el servicio que creamos para este fin
           UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
           //Ya ya solo queda generar un jwt con dicho userDetails
           String jwt = jwtUtil.generateToken(userDetails);

           //Si va bien, retornamos un AuthenticationResponse(OutputDTO) mandandole el jwt un httpStatus.OK
           return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
       }
       //Esta excepcion ocurre cuando no funcione bien la autenticacion(cuando no sea Pablo 12345)
       catch(BadCredentialsException b) {
           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
       }
    }
}
