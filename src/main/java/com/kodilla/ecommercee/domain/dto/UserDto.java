package com.kodilla.ecommercee.domain.dto;
import lombok.*;



@Getter
@AllArgsConstructor
public class UserDto {
   private Long id;
   private String username;
   private int status;
   private int userKey;
}
