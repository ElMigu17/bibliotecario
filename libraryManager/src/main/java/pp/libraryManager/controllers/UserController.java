package pp.libraryManager.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.DTOs.UserDTO;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.User;
import pp.libraryManager.service.BorrowService;
import pp.libraryManager.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<UserDTO> findAllUser() {
        return this.userService.findAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity addOneUsers(@RequestBody UserDTO userDto) {
        User user = this.userService.addOneBook(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
