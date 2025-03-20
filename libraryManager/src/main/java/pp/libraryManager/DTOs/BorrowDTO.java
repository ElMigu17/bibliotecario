package pp.libraryManager.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BorrowDTO {

    private Integer id;
    private UserDTO user;
    private BookDTO book;
    private Date borrow_date;
    private Date deliver_date;

}
