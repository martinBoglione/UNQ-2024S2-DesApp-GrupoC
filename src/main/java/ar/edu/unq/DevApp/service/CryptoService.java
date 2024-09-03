package ar.edu.unq.DevApp.service;

import ar.edu.unq.DevApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    @Autowired
    UserRepository userRepository;
}
