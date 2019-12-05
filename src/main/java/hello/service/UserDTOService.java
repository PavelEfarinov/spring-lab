package hello.service;

import hello.domain.User;
import hello.domain.UserDTO;
import hello.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDTOService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDTOService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(UserDTO userData) {
        User user = new User();
        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        userRepository.save(user);
//        userRepository.updatePasswordSha(user.getUserId(), userData.getUsername(), userData.getPassword());
        return user;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByUsername(login) == 0;
    }

//    public UserDTO findByLoginAndPassword(String login, String password) {
//        return new UserDTO(login == null || password == null ? null : userRepository.findByUsernameAndPassword(login, password));
//    }
//
//    public UserDTO findById(Long id) {
//        return new UserDTO(id == null ? null : userRepository.findById(id).orElse(null));
//    }

    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDTO(userRepository.findByUsername(username));
    }
}
