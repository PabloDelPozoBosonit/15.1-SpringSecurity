package formacionBackend5.Login.domain;


import formacionBackend5.Login.domain.dto.UserPInputDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Getter
@Setter
@Entity(name ="usuario") //Indicamos a JPA que esta clase es una entidad para que la pueda utilizar en su entorno de persistencia.
//@Table(name = "usuario") //Con esta anotación le indicamos a JPA a qué tabla hace referencia esta entidad que estamos creando.


public class UserP {


    @GeneratedValue
    @Id
    private Integer idUser;

    @Column(name = "usuario")
    private String user;

    private int age;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;

    public UserP(String user, int age, String password, String name, String surname, String companyEmail, String personalEmail, String city, boolean active, Date createdDate, String imagenUrl, Date terminationDate ) {

        this.user = user;
        this.age = age;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.companyEmail = companyEmail;
        this.personalEmail = personalEmail;
        this.city = city;
        this.active = active;
        this.createdDate = createdDate;
        this.imagenUrl = imagenUrl;
        this.terminationDate = terminationDate;
    }
    public UserP() {}


    public  void createUser(UserPInputDTO userPInputDTO) {


        this.user = userPInputDTO.getUser();
        this.age = userPInputDTO.getAge();
        this.password = userPInputDTO.getPassword();
        this.name = userPInputDTO.getName();
        this.surname = userPInputDTO.getSurname();
        this.companyEmail = userPInputDTO.getCompanyEmail();
        this.personalEmail = userPInputDTO.getPersonalEmail();
        this.city = userPInputDTO.getCity();
        this.active = userPInputDTO.isActive();
        this.createdDate = userPInputDTO.getCreatedDate();
        this.imagenUrl = userPInputDTO.getImagenUrl();
        this.terminationDate = userPInputDTO.getTerminationDate();

    }

}




