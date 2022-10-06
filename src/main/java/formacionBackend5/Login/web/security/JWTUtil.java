package formacionBackend5.Login.web.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.impl.ClaimsHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    /*
    JWT es un estandar de codigo abierto basado en JSON para crear tokens de seguridad
    La autenticacion viaja en el header de la peticion
    Un JWT por dentro, esta dividido en 3 secciones separadas por .
    La primera seccion incluye el algoritmo y el tipo de token que se esta generando
    La segunda esta toda la informacion del token
    El ultimo es la firma, coje las dos primeras partes y las encripta segun el algoritmo deseado
    Tenemos que incluir la dependencia necesaria para utilizar esta clase
    * */
    private static final String KEY = "boson1tP@blo";


    //Este metodo genera un JWT, recibe un userDetails
    public String generateToken(UserDetails userDetails ) {

        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .sign(Algorithm.HMAC256(KEY));
        return token;
    }
    //Para hacer  esto bien, deberemos de crear un controlador que reciba un usuario y una contraseña, y
    //como respuesta, envie el jwt.//Antes de crear el controlador, es mejor crear un par de clases que permita configurar esto de una mejor manera
//Estas clases son domain.dto.AuthenticationRequest y domain.dto.AuthenticationResponse


}


