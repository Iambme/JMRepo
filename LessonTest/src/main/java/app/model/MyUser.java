package app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MyUsers")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NonNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Column(name = "login")
    private String login;
    @NonNull
    @Column(name = "password")
    private String password;


}
