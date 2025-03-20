package pp.libraryManager.service;

import org.springframework.stereotype.Service;
import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.DTOs.UserDTO;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.Borrow;
import pp.libraryManager.entities.User;
import pp.libraryManager.repositories.BookRepository;
import pp.libraryManager.repositories.BorrowRepository;
import pp.libraryManager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

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
            userDtoList.add(new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCpf()));
        }
        return userDtoList;
    }

    public User addOneBook(UserDTO userDto) {
        User user = new User(userDto);

        return this.userRepository.save(user);
    }
}