package com.gr.aureabank.services;

import com.gr.aureabank.dtos.UserDto;
import com.gr.aureabank.entities.User;
import com.gr.aureabank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDto> findAllLegacy() {
        List<UserDto> users = new ArrayList<>();
        List<User> usersEntities = repository.findAll();

        for(int i=0 ; i<usersEntities.size() ; i++) {
            User user = usersEntities.get(i);
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            users.add(userDto);
        }

        return users;
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream().map(
                user -> {
                    UserDto userDto = new UserDto();
                    userDto.setFirstName(user.getFirstName());
                    userDto.setLastName(user.getLastName());
                    return userDto;
                }
        ).collect(Collectors.toList());
    }
}
