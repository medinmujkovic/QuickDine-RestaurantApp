package ba.unsa.etf.rpr.domain.entities;

import ba.unsa.etf.rpr.domain.Idable;
import lombok.*;

import java.util.Date;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor

public class User implements Idable{
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Date dateOfBirth;
    private int roleId;

    public User(String username, String password, String email, String fullName, Date dateOfBirth, int roleId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.roleId = roleId;
    }
}
