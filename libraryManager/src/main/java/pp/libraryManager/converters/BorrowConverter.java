package pp.libraryManager.converters;

import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.Borrow;
import pp.libraryManager.entities.User;

public class BorrowConverter {

    public static Borrow toEntity (BorrowDTO borrowDTO, User user, Book book){
        Borrow borrow = new Borrow();

        borrow.setId(borrowDTO.getId());
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrow_date(borrowDTO.getBorrow_date());
        borrow.setDeliver_date(borrowDTO.getDeliver_date());
        borrow.setDelivered(borrowDTO.getDelivered());

        return borrow;
    }

    public static BorrowDTO toDTO (Borrow borrow){
        BorrowDTO borrowDTO = new BorrowDTO();

        borrowDTO.setId(borrow.getId());
        borrowDTO.setUser_id(borrow.getUser().getId());
        borrowDTO.setUser_name(borrow.getUser().getName());

        borrowDTO.setBook_id(borrow.getBook().getId());
        borrowDTO.setBook_author(borrow.getBook().getAuthor());
        borrowDTO.setBook_title(borrow.getBook().getTitle());

        borrowDTO.setBorrow_date(borrow.getBorrow_date());
        borrowDTO.setDeliver_date(borrow.getDeliver_date());
        borrowDTO.setDelivered(borrow.getDelivered());

        return borrowDTO;
    }
}
