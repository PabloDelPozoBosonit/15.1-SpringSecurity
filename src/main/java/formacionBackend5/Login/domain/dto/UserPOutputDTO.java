package formacionBackend5.Login.domain.dto;

import formacionBackend5.Login.domain.UserP;
import lombok.Data;

import java.util.Date;

@Data
public class UserPOutputDTO {


    private Integer idUser;
    private String user;
    private int age;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;



    public UserPOutputDTO(UserP userP) {

        this.idUser = userP.getIdUser();
        this.user = userP.getUser();
        this.age = userP.getAge();
        this.name = userP.getName();
        this.surname = userP.getSurname();
        this.companyEmail = userP.getCompanyEmail();
        this.personalEmail = userP.getPersonalEmail();
        this.city = userP.getCity();
        this.active = userP.isActive();
        this.createdDate = userP.getCreatedDate();
        this.imagenUrl = userP.getImagenUrl();
        this.terminationDate = userP.getTerminationDate();
    }
}
