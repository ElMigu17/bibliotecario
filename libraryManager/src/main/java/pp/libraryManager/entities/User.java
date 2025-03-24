package pp.libraryManager.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user", schema = "public")
@Data
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

}
