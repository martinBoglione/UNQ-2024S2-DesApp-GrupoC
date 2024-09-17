package ar.edu.unq.DevApp.service;

import ar.edu.unq.DevApp.model.User;
import ar.edu.unq.DevApp.model.exceptions.*;
import ar.edu.unq.DevApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        try {
            user.validateUser();
        } catch (InvalidNameException | InvalidEmailException | InvalidAddressException  | InvalidCVUException | InvalidPasswordException | InvalidWalletException e) {
            throw e;
        }
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
