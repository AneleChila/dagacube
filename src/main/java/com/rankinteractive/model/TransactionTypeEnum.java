package com.rankinteractive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionTypeEnum {

    WAGERING("WAGERING"), WINNING("WINNING");

    private String type;
}
