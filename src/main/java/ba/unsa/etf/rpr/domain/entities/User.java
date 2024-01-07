package ba.unsa.etf.rpr.domain.entities;

import ba.unsa.etf.rpr.domain.Idable;
import lombok.*;

import java.util.Date;

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
}
