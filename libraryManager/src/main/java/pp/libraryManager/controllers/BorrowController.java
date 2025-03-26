package pp.libraryManager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.entities.Borrow;
import pp.libraryManager.service.BorrowService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("/borrow")
    public List<BorrowDTO> findAllBorrow() {
        return this.borrowService.findAllBorrow();
    }

    @PostMapping("/borrow")
    public ResponseEntity addOneBorrow(@RequestBody BorrowDTO borrowDTO) {
        Borrow borrow = null;
        try {
            borrow = this.borrowService.addOneBorrow(borrowDTO);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(borrow, HttpStatus.OK);
    }

    @DeleteMapping("/borrow/{id}")
    public void deleteOneUser(@PathVariable Integer id) {
        this.borrowService.deleteById(id);
    }

    @PutMapping("/borrow")
    public ResponseEntity editUser(@RequestBody BorrowDTO borrowDto) {
        Borrow borrow = null;
        try {
            borrow = this.borrowService.updateOneBorrow(borrowDto);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(borrow, HttpStatus.OK);
    }

}
