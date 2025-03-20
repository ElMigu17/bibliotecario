package pp.libraryManager.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private String cpf;

}
