package pp.libraryManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.DTOs.UserDTO;

import java.util.Date;

@Entity
@Table(name = "borrow")
@Data
@NoArgsConstructor
public class Borrow {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_generator")
    @SequenceGenerator(name = "borrow_generator", sequenceName = "borrow_seq", allocationSize = 1)
    @Column(name="id", nullable=false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @Column(name="borrow_date", nullable=false)
    private Date borrow_date;
    @Column(name="deliver_date", nullable=false)
    private Date deliver_date;

    public Borrow(BorrowDTO borrowDto, User user, Book book){
        this.user = user;
        this.book = book;
        this.borrow_date = borrowDto.getBorrow_date();
        this.deliver_date = borrowDto.getDeliver_date();
    }
}
