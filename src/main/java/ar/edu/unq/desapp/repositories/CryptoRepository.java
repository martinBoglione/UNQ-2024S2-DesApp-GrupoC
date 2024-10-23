package ar.edu.unq.desapp.repositories;

import ar.edu.unq.desapp.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, String> {
}
