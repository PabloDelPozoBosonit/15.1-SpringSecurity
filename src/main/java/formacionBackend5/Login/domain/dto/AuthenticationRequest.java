package formacionBackend5.Login.domain.dto;

import lombok.Getter;
import lombok.Setter;

//Es como un inputDTO
@Getter
@Setter
public class AuthenticationRequest {

    private String email;
    private String password;
}
