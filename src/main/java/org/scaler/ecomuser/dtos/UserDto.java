package org.scaler.ecomuser.dtos;

import lombok.Getter;
import lombok.Setter;
import org.scaler.ecomuser.models.EcomUser;
import org.scaler.ecomuser.models.Role;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private String name;

    private String email;

    private List<Role> roles;

    public static UserDto convertEcomUserToUserDto(EcomUser ecomUser) {
        UserDto userDto = new UserDto();
        userDto.setEmail(ecomUser.getEmail());
        userDto.setName(ecomUser.getName());
        userDto.setRoles(ecomUser.getRoles());
        return userDto;
    }
}
