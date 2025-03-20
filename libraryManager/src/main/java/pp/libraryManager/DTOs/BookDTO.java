package pp.libraryManager.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {

    private Integer id;
    private String title;
    private String author;
}
