package pp.libraryManager.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pp.libraryManager.DTOs.UserDTO;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @Column(name="id", nullable=false)
    private Integer id;

    @Column(name="name", nullable=false)
    private String name;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="cpf", nullable=false)
    private String cpf;

    public User(UserDTO userDto){
        this.id = userDto.getId();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.cpf = userDto.getCpf();
    }
}
