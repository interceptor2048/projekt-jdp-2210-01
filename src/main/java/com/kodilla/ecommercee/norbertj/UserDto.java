package com.kodilla.ecommercee.norbertj;
import lombok.*;



@Getter
@AllArgsConstructor
public class UserDto {
   private Long id;
   private String username;
   private int status;
   private int userKey;
}
