package pp.libraryManager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {

    private Integer id;
    private String title;
    private String author;
}
