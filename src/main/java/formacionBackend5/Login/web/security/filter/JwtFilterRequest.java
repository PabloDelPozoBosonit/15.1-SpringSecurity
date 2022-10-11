package formacionBackend5.Login.web.security.filter;


import formacionBackend5.Login.web.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static formacionBackend5.Login.web.config.ConstantConfiguration.TOKEN_PREFIX;


@Slf4j
@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Token completo
        String authorizationHeader = request.getHeader("Authorization");
        log.info("HEADER DE REQUEST: " + authorizationHeader);

        //Si no es nulo y empieza por Bearer...
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {

           //Obtenemos el token sin el prefijo
            String token = authorizationHeader.substring(7);//7 por Bearer y el espacio
            log.info("TOKEN:  " + token);

            //Obtenemos el payload que es el primer bloque de digitos antes del primer . del token
            String payload = token.split("\\.")[1];
            log.info("PAYLOAD:  " + payload);

            //Lo decodificamos
            String payloadDecode = new String(Base64.decodeBase64(payload),"UTF-8");
            log.info("PAYLOAD-DECODE:  " + payloadDecode);

            //lo obtenemos separado por comillas
            String[] payloadDecodeSeparado = payloadDecode.split("\"");

            //Del payload separado, el que nos interesa es el 3
            String email = payloadDecodeSeparado[3];
            log.info("EMAIL: " + email);

            //Se lo mandamos a loadByUsername
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            //Y  comprobamos que es el
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
