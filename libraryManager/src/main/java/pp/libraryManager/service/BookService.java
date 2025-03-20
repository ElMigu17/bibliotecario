package pp.libraryManager.service;

import org.springframework.stereotype.Service;
import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.converters.BookConverter;
import pp.libraryManager.entities.Book;
import pp.libraryManager.repositories.BookRepository;
import pp.libraryManager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private final UserRepository userRepository;
    private BookConverter bookConverter;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<BookDTO> findAllBook() {
        List<Book> books = (List<Book>) this.bookRepository.findAll();
        List<BookDTO> bookDtoList = new ArrayList<>();
        for(Book book : books){
            bookDtoList.add(bookConverter.toDTO(book));
        }
        return bookDtoList;
    }

    public Book addOneBook(BookDTO bookDTO) {
        Book book = bookConverter.toEntity(bookDTO);

        return this.bookRepository.save(book);
    }
/*
    public void deleteById(Integer id) {
        this.empresaRepository.deleteById(id);
    }

 */

}
