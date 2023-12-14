package ba.unsa.etf.rpr.controllers;

import java.util.Objects;

public final class RegisterUserMapper {
    private String fullName;
    private String username;
    private String email;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String usernameId) {
        this.username = usernameId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public RegisterUserMapper(String fullName, String username, String email, String dayOfBirth, 
                              String monthOfBirth, String yearOfBirth, String password) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterUserMapper that = (RegisterUserMapper) o;
        return Objects.equals(getFullName(), that.getFullName()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getDayOfBirth(), that.getDayOfBirth()) && Objects.equals(getMonthOfBirth(), that.getMonthOfBirth()) && Objects.equals(getYearOfBirth(), that.getYearOfBirth()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getUsername(), getEmail(), getDayOfBirth(), getMonthOfBirth(), getYearOfBirth(), getPassword());
    }

    @Override
    public String toString() {
        return "RegisterUserMapper{" +
                "fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", monthOfBirth='" + monthOfBirth + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}