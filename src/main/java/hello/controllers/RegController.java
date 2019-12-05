package hello.controllers;

import hello.domain.UserDTO;
import hello.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RegController {

    @Autowired
    private UserDTOService userDTOService;

    @RequestMapping("/registration")
    public void addUser(UserDTO user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!userDTOService.isLoginVacant(user.getUsername())) {
            response.sendRedirect("/reg?error");
        } else {
            userDTOService.register(user);
            request.login(user.getUsername(), user.getPassword());
            response.sendRedirect("/main?reg");
        }
    }
}
