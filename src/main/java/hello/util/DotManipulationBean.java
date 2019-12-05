package hello.util;

import hello.domain.Dot;
import hello.domain.DotDTO;
import hello.domain.User;
import hello.domain.UserDTO;
import hello.repository.UserRepository;
import hello.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DotManipulationBean {
    @Autowired
    private UserRepository userRepository;

    public List<Dot> dotListFromRequest(DotDTO request, String owner) {
        List<Dot> list = new ArrayList<>();
        for (double x : request.getX()) {
            Dot d = new Dot(x, request.getY(), request.getR(), false);
            d.setResult(checkDotArea(d));
            d.setOwner(userRepository.findByUsername(owner));
            list.add(d);
        }
        return list;
    }

    public boolean checkDotArea(Dot dot) {
        double x = dot.getX();
        double y = dot.getY();
        double r = dot.getR();
//        if ((x <= 0 && x >= -r && y >= 0 && y <= r / 2) ||
//                (x >= 0 && y >= 0 && r - x * 2 >= y) ||
//                (x >= 0 && y <= 0 && x * x + y * y <= r * r))
        return (x >= -r) && (x <= 0) && (y <= 0.5 * r) && (y >= 0) ||
                (-2 * x + r >= y) && (x >= 0) && (y >= 0) && (x <= r * 0.5) ||
                (x * x + y * y <= r * r) && (y <= 0) && (x >= 0);
    }
}
