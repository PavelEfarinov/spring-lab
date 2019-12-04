package hello.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WEBUSER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String username;
    private String password;

    @OneToMany
    private List<Dot> userDots;

    public User(String username, String password, List<Dot> userDots) {
        this.username = username;
        this.password = password;
        this.userDots = userDots;
    }

    public User() {
    }

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public List<Dot> getUserDots() {
        return userDots;
    }

    public void setUserDots(List<Dot> userDots) {
        this.userDots = userDots;
    }
}
