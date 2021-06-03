package com.rankinteractive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionStatus {

    ONLINE("online"), OFFLINE("offline");

    private String status;
}
