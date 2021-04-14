package com.rankinteractive.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeductionOrDepositRequest {

    @NotNull(message = "playerId cannot be empty")
    private Long playerId;
    @NotNull(message = "transactionId cannot be empty")
    private Long transactionId;
    @NotNull(message = "amount cannot be empty")
    @PositiveOrZero
    private Integer amount;

    private String promoCode;
}
