package formacionBackend5.Login.web.security.filter;


import formacionBackend5.Login.domain.dto.AuthenticationRequest;
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

            //Obtenemos el payload que es el primer bloque de digitos antes del primer .
            String payload = token.split("\\.")[1];
            log.info("PAYLOAD:  " + payload);

            //Lo decodificamos
            String payloadDecode = new String(Base64.decodeBase64(payload),"UTF-8");
            log.info("PAYLOAD-DECODE:  " + payloadDecode);

            //Y obtenemos el email {"sub":"jesus@mail.com"
            String[] payloadDecodeSeparado = payloadDecode.split("\"");

            String email = payloadDecodeSeparado[3];
            log.info("EMAIL: " + email);


            //Con esto simulo el metodo que extrae el username con el token, como es Jwts y esta deprecated, lo apaño asi de momento
            //String email = "jesus@mail.com";
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
