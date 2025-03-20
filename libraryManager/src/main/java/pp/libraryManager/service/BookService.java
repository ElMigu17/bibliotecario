package pp.libraryManager.service;

import org.springframework.stereotype.Service;
import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.entities.Book;
import pp.libraryManager.repositories.BookRepository;
import pp.libraryManager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private final UserRepository userRepository;
    private final long adulthoodInMilisecond = 568036800000L;
    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<BookDTO> findAllBook() {
        List<Book> books = (List<Book>) this.bookRepository.findAll();
        List<BookDTO> bookDtoList = new ArrayList<>();
        for(Book book : books){
            bookDtoList.add(new BookDTO(book.getId(), book.getAuthor(), book.getTitle()));
        }
        return bookDtoList;
    }

    public Book addOneBook(BookDTO empresaDto) {
        Book book = new Book(empresaDto);

        return this.bookRepository.save(book);
    }
/*
    private String validateFornecedor(Empresa empresa){
        String errors = "";

        if(this.checkIfCNPJIsUsed(empresa.getCNPJ(), empresa.getId())){
            errors += "Esse CNPJ já está sendo usado \n";
        }


        if(empresa.getEstado().equals("Paraná")){
            Date now = new Date();
            for(Fornecedor fornecedor : empresa.getFornecedor()){
                if(!fornecedor.getIs_pessoa_fisica()){
                    continue;
                }
                long ageInMilisecond = fornecedor.getData_nascimento().getTime() - now.getTime();
                if(adulthoodInMilisecond > ageInMilisecond){
                    errors += "Devido ao fato de a empresa ser do Paraná, ela não pode ter um fornecedor com menos de 18 anos \n";
                    break;
                }
            }
        }


        return errors;
    }

    public boolean checkIfCNPJIsUsed(String cnpj, Integer id){
        List<Empresa> empresas = this.empresaRepository.findByCNPJ(cnpj);

        return !(this.fornecedorRepository.findByCNPJ(cnpj).isEmpty() &&
                (empresas.isEmpty() || empresas.get(0).getId() == id));
    }

    public void deleteById(Integer id) {
        this.empresaRepository.deleteById(id);
    }

 */

}
