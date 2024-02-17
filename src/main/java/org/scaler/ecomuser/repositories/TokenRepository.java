package org.scaler.ecomuser.repositories;

import org.scaler.ecomuser.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValueAndIsDeleted(String value, boolean isDeleted);

    Optional<Token> findByValueAndIsDeletedAndExpiryAtGreaterThan(String value, boolean isDeleted, Date expiryDate);
}
