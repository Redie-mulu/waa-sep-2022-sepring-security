package edu.miu.service.imp;



import edu.miu.dto.UserDto;
import edu.miu.entity.User;
import edu.miu.repo.UserRepo;
import edu.miu.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDto> findAll() {
        return mapUsersToUserDtos(userRepo.findAll());
    }

    private List<UserDto> mapUsersToUserDtos (List<User> users) {
       List<UserDto> dtoList = new ArrayList<>();
        for(User u : users) {
            dtoList.add(modelMapper.map(u, UserDto.class));
        }
        return dtoList;
    }

    private List<UserDto> mapUsersToUserDtos(Iterable<User> userIterable) {
        List<UserDto> dtoList = new ArrayList<>();

        for(User user : userIterable) {
            dtoList.add(modelMapper.map(user,UserDto.class));
        }

        return dtoList;
    }

    @Override
    public UserDto findUserById(int id) {
        return mapUserToUserDto(userRepo.findById(id).get());
    }

    private UserDto mapUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUserById(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public void save(UserDto userDto) {
        User user =  userRepo.save(mapUserDtoToUser(userDto));
         mapUserToUserDto(user);
    }
    private User mapUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public void update(int id, UserDto userDto) {
        /**
         * For update operations:
         *
         * First get the object using a find (e.g. find by id or find first etc)
         * Then update the object with the data passed in, in this case that's your UserDTO object
         * Then save.
         *
         * If your service is transactional (i.e. @Transactional,)
         * After getting the object, calling the setter would automatically update the entity
         */
       save(userDto);
    }
}
