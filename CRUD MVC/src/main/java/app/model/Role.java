package app.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @Transient
    @ManyToMany(mappedBy = "roles",cascade=CascadeType.ALL)
    private Set<MyUser> myUsers;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role(Long id, String role, Set<MyUser> myUsers) {
        this.id = id;
        this.role = role;
        this.myUsers = myUsers;
    }

    public Set<MyUser> getMyUsers() {
        return myUsers;
    }

    public void setMyUsers(Set<MyUser> myUsers) {
        this.myUsers = myUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}