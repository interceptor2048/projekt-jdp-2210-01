package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserInputDto {
   private Long id;
   private String firstName;
   private String lastName;
   private String address;
   private String email;
   private String login;
   private String password;
   private LocalDate creationDate;
   private Long key;
   private int status;
   private List<OrderEntity> orders;
}
