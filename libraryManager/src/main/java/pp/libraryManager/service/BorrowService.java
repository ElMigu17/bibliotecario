package pp.libraryManager.service;

import org.springframework.stereotype.Service;
import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.controllers.BorrowController;
import pp.libraryManager.converters.BorrowConverter;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.Borrow;
import pp.libraryManager.repositories.BookRepository;
import pp.libraryManager.repositories.BorrowRepository;
import pp.libraryManager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BorrowService {

    private BorrowRepository borrowRepository;
    private UserService userService;
    private BookService bookService;

    public BorrowService(BorrowRepository borrowRepository, UserService userService, BookService bookService) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    public List<BorrowDTO> findAllBorrow() {
        List<Borrow> borrows = (List<Borrow>) this.borrowRepository.findAll();
        List<BorrowDTO> borrowDtoList = new ArrayList<>();
        for(Borrow borrow : borrows){
            borrowDtoList.add(BorrowConverter.toDTO(borrow));
        }
        return borrowDtoList;
    }

    public Borrow addOneBorrow(BorrowDTO borrowDTO) {
        Borrow borrow = new Borrow();
        borrow.setUser(userService.findOneUser(borrowDTO.getUser_id()));
        borrow.setBook(bookService.findOneBook(borrowDTO.getBook_id()));
        borrow.setBorrow_date(new Date());
        borrow.setDeliver_date(borrowDTO.getDeliver_date());
        borrow.setDelivered(false);

        return this.borrowRepository.save(borrow);
    }

    public void deleteById(Integer id) {
        this.borrowRepository.deleteById(id);
    }

    public Borrow updateOneBorrow(BorrowDTO borrowDTO) {
        Borrow borrow = new Borrow();
        borrow.setId(borrowDTO.getId());
        borrow.setUser(userService.findOneUser(borrowDTO.getUser_id()));
        borrow.setBook(bookService.findOneBook(borrowDTO.getBook_id()));
        borrow.setBorrow_date(borrowDTO.getBorrow_date());
        borrow.setDeliver_date(borrowDTO.getDeliver_date());
        borrow.setDelivered(borrowDTO.getDelivered());

        return this.borrowRepository.save(borrow);
    }
}
