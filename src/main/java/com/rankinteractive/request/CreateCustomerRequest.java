package com.rankinteractive.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {


    //@profile---------------
    @NotBlank(message = "firstName cannot be empty")
    private String firstName;

    @NotBlank(message = "lastName cannot be empty")
    private String lastName;


    //@credentials-----------
    @NotBlank(message = "emailAddress cannot be empty")
    private String emailAddress;

    @NotBlank(message = "password cannot be empty")
    private String password;
}
