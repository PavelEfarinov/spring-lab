package hello.controllers;

import hello.domain.UserDTO;
import hello.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.*;

@Controller
public class RegistrationController {
    private final UserDTOService userDTOService;

    public RegistrationController(UserDTOService userDTOService) {
        this.userDTOService = userDTOService;
    }

    @PostMapping("/registration_test")
    public String addUser(@RequestBody UserDTO user) {
        if (userDTOService.isLoginVacant(user.getUsername())) {
            userDTOService.register(user);
        }
        return "/index";
    }

    @GetMapping("/registration")
    public String sendPage() {
        return "/registration";
    }
}
