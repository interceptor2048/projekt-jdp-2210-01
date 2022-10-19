package com.kodilla.ecommercee.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
    private LocalDate creationDate= LocalDate.now();

    @Column(name="user_current_key")
    private Long key;

    @Column(name="user_status")
    private int status;

    @OneToMany(targetEntity = OrderEntity.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
   private List<OrderEntity> orders = new ArrayList<>();
}
