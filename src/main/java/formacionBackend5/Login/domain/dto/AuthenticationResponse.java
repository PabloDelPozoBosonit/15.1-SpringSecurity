package formacionBackend5.Login.domain.dto;

import lombok.Getter;
import lombok.Setter;

//OutputDTO, recibe jwt, se lo asigna, ademas con lombok creamos getters and setters
@Getter
@Setter
public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
