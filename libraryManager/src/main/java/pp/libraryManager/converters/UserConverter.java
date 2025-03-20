package pp.libraryManager.converters;

import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.DTOs.UserDTO;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.User;

public class UserConverter {

    public User toEntity (UserDTO userDTO){
        User user = new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());

        return user;
    }

    public UserDTO toDTO (User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
