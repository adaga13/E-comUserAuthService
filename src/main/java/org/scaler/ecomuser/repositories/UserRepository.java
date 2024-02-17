package org.scaler.ecomuser.repositories;

import org.scaler.ecomuser.models.EcomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<EcomUser, Long> {

    Optional<EcomUser> findByEmail(String email);
}
