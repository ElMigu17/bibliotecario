package pp.libraryManager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.entities.Book;
import pp.libraryManager.service.BookService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookDTO> findAllBook() {
        return this.bookService.findAllBook();
    }

    @PostMapping("/books")
    public ResponseEntity addOneBook(@RequestBody BookDTO bookDto) {
        Book book = null;
        try {
            book = this.bookService.addOneBook(bookDto);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
