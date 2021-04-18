package com.rankinteractive.controller;

import com.rankinteractive.model.TonicEvent;
import com.rankinteractive.service.LoginService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author anelechila
 */
@RestController
@RequestMapping("/")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public List<TonicEvent> login(@RequestParam(value = "emailAddress", required = true) String emailAddress,
                                  @RequestParam(value = "password", required = true)String password) {


        return loginService.login(emailAddress, password);

    }

    @RequestMapping(value = "logout/{id}", method = RequestMethod.POST)
    public List<TonicEvent> logout(@RequestParam(value = "emailAddress", required = true) String emailAddress,
                                  @RequestParam(value = "password", required = true)String password) {


        return loginService.login(emailAddress, password);

    }
}
