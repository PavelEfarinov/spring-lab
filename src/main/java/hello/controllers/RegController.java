package hello.controllers;

import hello.domain.User;
import hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RegController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping("/registration")
    public void addUser(User user,HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userDB = userRepo.findByUsername(user.getUsername());

        if (userDB != null){
            response.sendRedirect("/reg?error");
            return;
        }
        userRepo.save(user);
        request.login(user.getUsername(), user.getPassword());
        response.sendRedirect("/main?reg");
    }
}
