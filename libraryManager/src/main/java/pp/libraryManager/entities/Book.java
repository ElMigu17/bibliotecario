package pp.libraryManager.entities;

import jakarta.persistence.*;
import lombok.Data;
import pp.libraryManager.DTOs.BookDTO;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @Column(name="id", nullable=false)
    private Integer id;

    @Column(name="title", nullable=false)
    private String title;
    @Column(name="author", nullable=false)
    private String author;

    public Book(BookDTO bookDTO){
        this.id = bookDTO.getId();
        this.title = bookDTO.getTitle();
        this.author = bookDTO.getAuthor();
    }
}
