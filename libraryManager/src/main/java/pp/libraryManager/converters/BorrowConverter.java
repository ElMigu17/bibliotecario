package pp.libraryManager.converters;

import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.Borrow;

public class BorrowConverter {

    private UserConverter userConverter;
    private BookConverter bookConverter;

    public Borrow toEntity (BorrowDTO borrowDTO){
        Borrow borrow = new Borrow();

        borrow.setId(borrowDTO.getId());
        borrow.setUser(userConverter.toEntity(borrowDTO.getUser()));
        borrow.setBook(bookConverter.toEntity(borrowDTO.getBook()));
        borrow.setBorrow_date(borrowDTO.getBorrow_date());
        borrow.setDeliver_date(borrowDTO.getDeliver_date());

        return borrow;
    }

    public BorrowDTO toDTO (Borrow borrow){
        BorrowDTO borrowDTO = new BorrowDTO();

        borrowDTO.setId(borrow.getId());
        borrowDTO.setUser(userConverter.toDTO(borrow.getUser()));
        borrowDTO.setBook(bookConverter.toDTO(borrow.getBook()));
        borrowDTO.setBorrow_date(borrow.getBorrow_date());
        borrowDTO.setDeliver_date(borrow.getDeliver_date());

        return borrowDTO;
    }
}
