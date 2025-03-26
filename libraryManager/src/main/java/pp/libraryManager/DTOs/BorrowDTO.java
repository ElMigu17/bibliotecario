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
    private Integer user_id;
    private String user_name;
    private Integer book_id;
    private String book_title;
    private String book_author;
    private Date borrow_date;
    private Date deliver_date;
    private Boolean delivered;

}
