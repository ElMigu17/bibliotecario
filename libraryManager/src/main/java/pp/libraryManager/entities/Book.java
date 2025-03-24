package pp.libraryManager.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "book_seq", allocationSize = 1)
    @Column(name="id", nullable=false)
    private Integer id;

    @Column(name="title", nullable=false)
    private String title;
    @Column(name="author", nullable=false)
    private String author;

}
