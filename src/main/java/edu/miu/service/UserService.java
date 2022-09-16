package edu.miu.service;


import edu.miu.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findUserById(int id);
    void deleteUserById(int id);
    void save(UserDto userDto);
    void update(int id, UserDto userDto);

}
