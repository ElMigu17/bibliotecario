package pp.libraryManager.service;

import org.springframework.stereotype.Service;
import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.converters.BookConverter;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.User;
import pp.libraryManager.repositories.BookRepository;
import pp.libraryManager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<BookDTO> findAllBook() {
        List<Book> books = (List<Book>) this.bookRepository.findAll();
        List<BookDTO> bookDtoList = new ArrayList<>();
        for(Book book : books){
            bookDtoList.add(BookConverter.toDTO(book));
        }
        return bookDtoList;
    }

    public Book findOneBook(Integer id) {
        Optional<Book> book = this.bookRepository.findById(id);
        return book.orElse(null);
    }

    public Book addOneBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());

        return this.bookRepository.save(book);
    }

    public void deleteById(Integer id) {
        this.bookRepository.deleteById(id);
    }



}
