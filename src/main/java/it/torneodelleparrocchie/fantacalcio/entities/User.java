package it.torneodelleparrocchie.fantacalcio.entities;
/**
 * Created by dsalvatore on 21/06/17.
 */

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "utente")
@NamedQueries(
        @NamedQuery(name = "User.findByUsername",
                query = "select u from User u where u.username=:username")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_.\\-]+",
            message = "Username non valido")
    private String username;

    @Column(nullable = false)
    @NotNull
    @Length(min = 8)
    private String password;

    @Column(unique = true)
    @NotNull
    @Email(regexp = "[a-z0-9_.\\-]+@[a-z]+\\.[a-z]{2,4}",
            message = "Email non valida")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class Builder {

        private User user;

        private Builder builder;

        public Builder username(String username) {
            builder.getUser().username = username;
            return builder;
        }

        public Builder password(String password) {
            builder.getUser().password = password;
            return builder;
        }

        public Builder email(String email) {
            builder.getUser().email = email;
            return builder;
        }

        private User getUser() {
            return user;
        }

        public User create() {
            return builder.getUser();
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
