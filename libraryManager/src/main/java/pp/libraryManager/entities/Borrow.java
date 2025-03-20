package pp.libraryManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import pp.libraryManager.DTOs.UserDTO;

import java.util.Date;

@Entity
@Table(name = "borrow")
@Data
public class Borrow {


    @OneToMany()
    @Column(name="id_user", nullable=false)
    private Integer id_user;
    @OneToMany()
    @Column(name="id_book", nullable=false)
    private Integer id_book;

    @Column(name="borrow_date", nullable=false)
    private Date borrow_date;
    @Column(name="deliver_date", nullable=false)
    private Date deliver_date;

    public Borrow(UserDTO userDto){
        this.id = userDto.getId();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.cpf = userDto.getCpf();
    }
}
