package io.victorvld.domain.entities;

public class User extends Entity {
    private String email;
    private String password;
    private String lastName;
    private String firstName;

    private User(Builder builder) {
        super(builder);
        this.email = builder.email;
        this.password = builder.password;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Entity.Builder<Builder> {
        private String email;
        private String password;
        private String lastName;
        private String firstName;

        Builder() {
        }

        public Builder email(final String email) {
            this.email = email;
            return this;
        }

        public Builder password(final String password) {
            this.password = password;
            return this;
        }

        public Builder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
