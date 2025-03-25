package pp.libraryManager.service;

import org.springframework.stereotype.Service;
import pp.libraryManager.DTOs.UserDTO;
import pp.libraryManager.converters.UserConverter;
import pp.libraryManager.entities.User;
import pp.libraryManager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = (List<User>) this.userRepository.findAll();
        List<UserDTO> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(UserConverter.toDTO(user));
        }
        return userDtoList;
    }

    public User findOneUser(Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElse(null);
    }

    public User addOneUser(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setCpf(userDto.getCpf());

        return this.userRepository.save(user);
    }

    public void deleteById(Integer id) {
        this.userRepository.deleteById(id);
    }

    public User updateOneUser(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setCpf(userDto.getCpf());

        return this.userRepository.save(user);
    }
}