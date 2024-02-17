package org.scaler.ecomuser.controllers;

import org.scaler.ecomuser.dtos.LoginRequestDto;
import org.scaler.ecomuser.dtos.LogoutRequestDto;
import org.scaler.ecomuser.dtos.SignupRequestDto;
import org.scaler.ecomuser.dtos.UserDto;
import org.scaler.ecomuser.exceptions.InvalidTokenException;
import org.scaler.ecomuser.exceptions.UserNotFoundException;
import org.scaler.ecomuser.models.EcomUser;
import org.scaler.ecomuser.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public EcomUser signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) throws UserNotFoundException {
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        userService.logout(logoutRequestDto.getToken());
        return null;
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token) throws InvalidTokenException {
        return UserDto.convertEcomUserToUserDto(userService.validateToken(token));
    }

    @GetMapping
    public void getAll() {
        System.out.println("hhghjg");
    }

}