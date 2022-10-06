package formacionBackend5.Login.web.controller;

import formacionBackend5.Login.domain.service.UserPService;
import formacionBackend5.Login.domain.service.UserP;
import formacionBackend5.Login.domain.dto.UserPInputDTO;
import formacionBackend5.Login.domain.dto.UserPOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "user")
@RestController
public class ControllerUserP {

    @Autowired
    UserPService usuarioService;

    /*
     *
     * Al ser Post, no es necesario indicarle el valor
     * Recibimos un JSON UserinputDTO
     * Se lo mandamos a la capa de servicio
     * */
    @PostMapping
    public UserPOutputDTO insertUser(@RequestBody UserPInputDTO userPInputDTO) throws Exception {

        return usuarioService.createUser(userPInputDTO);
    }


    //Recibimos el id y se lo pasamos a la capa bde servicio
    @GetMapping(value = "/obtener/{id}")
    public UserPOutputDTO getUser(@PathVariable Integer id) throws Exception {

        return usuarioService.getUser(id);
    }

    //Recibimos los datos a actualizar y el id y se lo pasamos a la capa bde servicio
    @PostMapping(value = "/actualizar/{id}")
    public UserPOutputDTO updateUser(@RequestBody UserPInputDTO userPInputDTO, @PathVariable Integer id) throws Exception {

        return usuarioService.updateUser(userPInputDTO, id);

    }


    @DeleteMapping(value = "/eliminar/{id}")
    public void deleteUser(@PathVariable Integer id) {
        usuarioService.deleteUser(id);
    }


    @GetMapping(value = "/findByName/{name}")
    public List<UserP> findByName(@PathVariable String name) {
        return usuarioService.findByName(name);
    }


    @GetMapping(value = "/findAll")
    public List<UserP> findAll() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Datos del usuario:" + auth.getPrincipal());
        System.out.println("Datos de los permisos" + auth.getAuthorities());
        System.out.println("Esta autenticado" + auth.isAuthenticated());
        return usuarioService.findAll();
    }




}
