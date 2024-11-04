package ar.edu.unq.desapp.service;

import ar.edu.unq.desapp.model.User;
import ar.edu.unq.desapp.model.exceptions.*;
import ar.edu.unq.desapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        try {
            user.validateUser();
        } catch (InvalidNameException | InvalidEmailException | InvalidAddressException  | InvalidCVUException | InvalidPasswordException | InvalidWalletException e) {
            throw e;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findActiveUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAllActiveUsers();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userRepository.softDeleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
