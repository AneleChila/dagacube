package com.rankinteractive.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {

    //@profile---------------
    @Size(min = 1, message = "firstName cannot be empty")
    private String firstName;

    @Size(min = 1, message = "lastName cannot be empty")
    private String lastName;

    @Size(min = 1, message = "idNumber cannot be empty")
    private String idNumber;

    @Size(min = 1, message = "gender cannot be empty")
    private String gender;

    @Size(min = 1, message = "mobileNUmber cannot be empty")
    private String mobileNUmber;


    //@credentials-----------
    @Size(min = 1, message = "emailAddress cannot be empty")
    private String emailAddress;

    @Size(min = 1, message = "password cannot be empty")
    private String password;
}
