package com.kodilla.ecommercee.norbertj;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_first_name")
    private String firstName;

    @Column(name="user_last_name")
    private String lastName;

    @Column(name="user_address")
    private String address;

    @Column(name="user_email")
    private String email;

    @Column(name="user_login")
    private String login;

    @Column(name="user_password")
    private String password;

    @Column(name="user_account_creation_date")
    private LocalDate creationDate;

    @Column(name="user_current_key")
    private Long key;

    //added based on spec.json
    @Column(name="user_status")
    private int status;

      //commented out so the class compile without reference class
      @OneToMany(mappedBy = "userId")
      @JsonManagedReference
      private List<OrderEntity> orders;

}
