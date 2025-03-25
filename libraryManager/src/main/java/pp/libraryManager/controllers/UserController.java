package pp.libraryManager.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.libraryManager.DTOs.UserDTO;
import pp.libraryManager.entities.User;
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
        User user = this.userService.addOneUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public void deleteOneUser(@PathVariable Integer id) {
        this.userService.deleteById(id);
    }

    @PutMapping("/users")
    public ResponseEntity editUser(@RequestBody UserDTO userDto) {
        User user = null;
        try {
            user = this.userService.updateOneUser(userDto);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
