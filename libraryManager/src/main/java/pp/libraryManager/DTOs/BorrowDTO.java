package pp.libraryManager.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BorrowDTO {

    private Integer id_user;
    private Integer id_book;
    private Date borrow_date;
    private Date deliver_date;

}
