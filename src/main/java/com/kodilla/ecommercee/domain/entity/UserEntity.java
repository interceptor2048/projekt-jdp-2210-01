package com.kodilla.ecommercee.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @NonNull
    @Column(name="user_first_name")
    private String firstName;

    @NonNull
    @Column(name="user_last_name")
    private String lastName;

    @NonNull
    @Column(name="user_address")
    private String address;

    @NonNull
    @Column(name="user_email")
    private String email;

    @NonNull
    @Column(name="user_login")
    private String login;

    @NonNull
    @Column(name="user_password")
    private String password;

    @Column(name="user_account_creation_date")
    private LocalDate creationDate = LocalDate.now();

    @NonNull
    @Column(name="user_current_key")
    private Long key;

    @NonNull
    @Column(name="user_status")
    private int status;

    @OneToMany(targetEntity = OrderEntity.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<OrderEntity> orders = new ArrayList<>();
}
