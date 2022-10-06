package formacionBackend5.Login.web.config;

import formacionBackend5.Login.domain.service.UserDetailsService;
import formacionBackend5.Login.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Asi le indicamos a Spring que esta clase se encarga de la seguridad
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Inyectamos nuestro servicio
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //La autenticacion la manejamos nosotros, le pasamos nuestro servicio, que es la clase domain.service.UserDetailService
        auth.userDetailsService(userDetailsService);
        //Al hacer esto, ya no genera una contraseña automaticamente, para poder acceder tendremos que escribir nuestra contraseña y usuario
    }

    //El http que recibe en el parametro, deshabiliotamos csrf(peticiones cruzadas)
    //Autoriza las peticiones que terminen en authenticate, los asteriscos son que no impoprta lo que este escrito antes
    //Y despues, decimos que las demas Request, SI necesitan authenticacion
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    //Estoes para que spring maneje la autenticacion
    //Al añadirle @bean, lo estamos eligiendo como gestor de autenticacion de la aplicacion
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
