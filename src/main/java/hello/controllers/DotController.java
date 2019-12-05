package hello.controllers;

import hello.domain.DotDTO;
import hello.domain.UserDTO;
import hello.service.DotDTOService;
import hello.util.DotManipulationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class DotController {
    @Autowired
    private DotManipulationBean dotManipulationBean;

    @Autowired
    private DotDTOService dotDTOService;

    @PostMapping("/dots")
    @ResponseBody
    public String addDot(@Valid @RequestBody DotDTO request, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorString = new StringBuilder();
            for (ObjectError e : bindingResult.getAllErrors()) {
                errorString.append(e.getDefaultMessage()).append('\n');
            }
            return errorString.toString();
        } else {
            System.out.println(principal.getName());
            dotDTOService.save(request, principal.getName());
            return "ok";
        }
    }

    @GetMapping("/dots")
    @ResponseBody
    public List<DotDTO> getAllDots(Principal principal) {
        return dotDTOService.loadUserDots(principal.getName());
    }

    @RequestMapping("/clear")
    public void clear(Principal principal) {
        dotDTOService.deleteUserDots(principal.getName());
    }
}
