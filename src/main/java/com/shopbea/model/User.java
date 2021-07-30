package com.shopbea.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Column(nullable = false)
    private String firstName;
    @NotEmpty
    @Column(nullable = false)
    private String lastName;
    @NotEmpty
    @Column(nullable = false,unique = true)
    @Email(message = "Please provide an valid email")
    private String email;
    @NotEmpty
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")}
            )
    private List<Role> roles;

    public User(User user)  {
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.password = user.password;
        this.roles = user.roles;
    }
    public User(){

    }
}
