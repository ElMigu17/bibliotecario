package pp.libraryManager.converters;

import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.entities.Book;

public class BookConverter {

    public static Book toEntity (BookDTO bookDTO){
        Book book= new Book();

        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());

        return book;
    }

    public static BookDTO toDTO (Book book){
        BookDTO bookDTO= new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());

        return bookDTO;
    }
}
