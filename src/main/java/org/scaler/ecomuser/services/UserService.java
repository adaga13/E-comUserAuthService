package org.scaler.ecomuser.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.scaler.ecomuser.exceptions.InvalidTokenException;
import org.scaler.ecomuser.exceptions.UserAlreadyExistsException;
import org.scaler.ecomuser.exceptions.UserNotFoundException;
import org.scaler.ecomuser.models.EcomUser;
import org.scaler.ecomuser.models.Token;
import org.scaler.ecomuser.repositories.TokenRepository;
import org.scaler.ecomuser.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public EcomUser signup(String name, String email, String password) {
        userRepository.findByEmail(email).ifPresent((val) -> {
            throw new UserAlreadyExistsException("User with email " + email + " is already registered.");
        });

        EcomUser ecomUser = EcomUser.builder().name(name)
                .email(email)
                .hashedPassword(passwordEncoder.encode(password))
                .build();

        return userRepository.save(ecomUser);
    }

    public String login(String email, String password) throws UserNotFoundException {
         EcomUser existingUser = userRepository.findByEmail(email)
                 .orElseThrow(() -> new UserNotFoundException("No user exists with given email. Please signup."));

         if (!passwordEncoder.matches(password, existingUser.getHashedPassword()))
             throw new UserNotFoundException("Bad credentials.");


        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setUser(existingUser);
        token.setExpiryAt(expiryDate);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        tokenRepository.save(token);

        return token.getValue();
    }

    public void logout(String stringToken) {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndIsDeleted(stringToken, false);
        if (tokenOptional.isPresent()) {
            Token token = tokenOptional.get();
            token.setIsDeleted(true);
            tokenRepository.save(token);
        }
    }

    public EcomUser validateToken(String stringToken) throws InvalidTokenException {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndIsDeletedAndExpiryAtGreaterThan(
                stringToken, false, new Date());
        return tokenOptional.map(Token::getUser).orElseThrow(() -> new InvalidTokenException("Provided token is either invalid " +
                "or expired."));
    }
}
