package hello.service;

import hello.domain.Dot;
import hello.domain.DotDTO;
import hello.repository.DotRepository;
import hello.repository.UserRepository;
import hello.util.DotManipulationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DotDTOService {
    private final DotRepository dotRepository;
    private DotManipulationBean dotManipulationBean;
    private final UserRepository userRepository;

    public DotDTOService(DotRepository dotRepository, DotManipulationBean dotManipulationBean, UserRepository userRepository) {
        this.dotRepository = dotRepository;
        this.dotManipulationBean = dotManipulationBean;
        this.userRepository = userRepository;
    }

    public void save(DotDTO dots, String owner) {
        List<Dot> dotList = dotManipulationBean.dotListFromRequest(dots, owner);
        for (Dot d : dotList) {
            dotRepository.save(d);
        }
    }

    public List<DotDTO> loadUserDots(String name) {
        List<Dot> fromDB = dotRepository.findAllByOwner(userRepository.findByUsername(name));
        List<DotDTO> toClient = new ArrayList<>();
        for(Dot d: fromDB)
        {
            toClient.add(new DotDTO(d));
        }
        return toClient;
    }

    @Transactional
    public void deleteUserDots(String name) {
        dotRepository.deleteAllByOwner(userRepository.findByUsername(name));
    }

}
