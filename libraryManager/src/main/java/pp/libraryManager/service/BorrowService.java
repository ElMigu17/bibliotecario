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
import java.util.List;

@Service
public class BorrowService {

    private BorrowRepository borrowRepository;
    private UserService userService;
    private BookService bookService;

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
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
        borrow.setUser(userService.findOneUser(borrowDTO.getUser().getId()));
        borrow.setBook(bookService.findOneBook(borrowDTO.getBook().getId()));
        borrow.setBorrow_date(borrowDTO.getBorrow_date());
        borrow.setDeliver_date(borrowDTO.getDeliver_date());

        return this.borrowRepository.save(borrow);
    }

    public void deleteById(Integer id) {
        this.borrowRepository.deleteById(id);
    }

}
