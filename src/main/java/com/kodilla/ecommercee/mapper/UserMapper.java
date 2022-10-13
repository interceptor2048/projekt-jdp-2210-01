package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.domain.entity.UserEntity;
import com.kodilla.ecommercee.errorhandlers.UserNotFoundException;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final UserDbService userDbService;

    public UserEntity mapToUserEntity(final UserDto userDto) throws UserNotFoundException {
        UserEntity userEntity = userDbService.getUser(userDto.getId());
        return new UserEntity(userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getAddress(),
                userEntity.getEmail(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                (long) userEntity.getStatus(),
                userEntity.getStatus()
        );
    }

    public UserDto mapToUserDto(final UserEntity userEntity) {
        return new UserDto(userEntity.getId(),
                        userEntity.getLogin(),
                        userEntity.getStatus(),
                        userEntity.getKey().intValue()
        );
    }

    public List<UserDto> mapToUsersListDto(final List<UserEntity> userEntityList) {
        return userEntityList.stream()
                .map(this :: mapToUserDto)
                .collect(Collectors.toList());
    }
}
