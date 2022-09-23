package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long cartId;
    private Long userId;
    private LocalDate orderCreationDate;
    private boolean orderIsClosed;
    private boolean orderIsDelivered;
}
