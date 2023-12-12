package ba.unsa.etf.rpr.controllers;

import java.util.Objects;

public final class LoginUserMapper {
    private String usernameId;
    private String passwordId;

    public LoginUserMapper(String usernameId, String passwordId) {
        this.usernameId = usernameId;
        this.passwordId = passwordId;
    }

    public String getUsernameId() {
        return usernameId;
    }

    public void setUsernameId(String usernameId) {
        this.usernameId = usernameId;
    }

    public String getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(String passwordId) {
        this.passwordId = passwordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginUserMapper that = (LoginUserMapper) o;
        return Objects.equals(usernameId, that.usernameId) && Objects.equals(passwordId, that.passwordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usernameId, passwordId);
    }

    @Override
    public String toString() {
        return "LoginUserMapper{" +
                "usernameId='" + usernameId + '\'' +
                ", passwordId='" + passwordId + '\'' +
                '}';
    }
}