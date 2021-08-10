package pl.snowboard4humans.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity(name = "Users")
public class User extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "full_name")
    private String fullName;
    @NotNull
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public User(Integer userId, String email, String fullName, String password) {
        this.id = userId;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer userId) {
        this.id = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
