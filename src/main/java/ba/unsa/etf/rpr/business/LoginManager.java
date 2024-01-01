package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.controllers.DTO.UserRequest;
import javafx.collections.ObservableList;

import static ba.unsa.etf.rpr.utils.UserRequestHelper.createUserRequests;

public class LoginManager {

    private String role=new String();
    public boolean authentication(String username, String password)
    {
        ObservableList<UserRequest>request=createUserRequests();
        for(UserRequest user:request)
        {
            if(user.username().equals(username) && user.password().equals(password)) {
                role=user.roles();
                return true;
            }
        }
        return false;
    }
    public String getRole()
    {
        return role;
    }
}
