package pp.libraryManager.converters;

import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.Borrow;

public class BorrowConverter {

    public static Borrow toEntity (BorrowDTO borrowDTO){
        Borrow borrow = new Borrow();

        borrow.setId(borrowDTO.getId());
        borrow.setUser(UserConverter.toEntity(borrowDTO.getUser()));
        borrow.setBook(BookConverter.toEntity(borrowDTO.getBook()));
        borrow.setBorrow_date(borrowDTO.getBorrow_date());
        borrow.setDeliver_date(borrowDTO.getDeliver_date());

        return borrow;
    }

    public static BorrowDTO toDTO (Borrow borrow){
        BorrowDTO borrowDTO = new BorrowDTO();

        borrowDTO.setId(borrow.getId());
        borrowDTO.setUser(UserConverter.toDTO(borrow.getUser()));
        borrowDTO.setBook(BookConverter.toDTO(borrow.getBook()));
        borrowDTO.setBorrow_date(borrow.getBorrow_date());
        borrowDTO.setDeliver_date(borrow.getDeliver_date());

        return borrowDTO;
    }
}
