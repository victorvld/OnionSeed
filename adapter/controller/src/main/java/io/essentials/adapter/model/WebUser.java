package io.essentials.adapter.model;

import io.essentials.domain.entities.User;

public class WebUser {
    private String email;
    private String password;
    private String lastName;
    private String firstName;

    public WebUser() {

    }

    public WebUser(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User toUser() {
        return User.builder()
                .email(email)
                .password(password)
                .lastName(lastName)
                .firstName(firstName)
                .build();
    }

    public static WebUser toUserWeb(final User user) {
        var userWeb = new WebUser();
        userWeb.setEmail(user.getEmail());
        // do not map password
        userWeb.setLastName(user.getLastName());
        userWeb.setFirstName(user.getFirstName());
        return userWeb;
    }
}
