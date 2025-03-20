package pp.libraryManager.service;

import org.springframework.stereotype.Service;
import pp.libraryManager.DTOs.BookDTO;
import pp.libraryManager.DTOs.BorrowDTO;
import pp.libraryManager.controllers.BorrowController;
import pp.libraryManager.converters.BorrowConverter;
import pp.libraryManager.entities.Book;
import pp.libraryManager.entities.Borrow;
import pp.libraryManager.repositories.BookRepository;
import pp.libraryManager.repositories.BorrowRepository;
import pp.libraryManager.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowService {

    private BorrowRepository borrowRepository;
    private BorrowConverter borrowConverter;
    public BorrowService(BorrowRepository borrowRepository, UserRepository userRepository) {
        this.borrowRepository = borrowRepository;
    }

    public List<BorrowDTO> findAllBorrow() {
        List<Borrow> borrows = (List<Borrow>) this.borrowRepository.findAll();
        List<BorrowDTO> borrowDtoList = new ArrayList<>();
        for(Borrow borrow : borrows){
            borrowDtoList.add(borrowConverter.toDTO(borrow));
        }
        return borrowDtoList;
    }
/*
    public Empresa addOneEmpresa(EmpresaDto empresaDto) {
        Empresa empresa = new Empresa(empresaDto);
        if(empresaDto.getFornecedores() == null) {
            return this.empresaRepository.save(empresa);
        }
        List<Fornecedor> fornecedoresList = (List<Fornecedor>) fornecedorRepository.findAllById(empresaDto.getFornecedores());

        empresa.setFornecedor(fornecedoresList);
        String errors = this.validateFornecedor(empresa);
        if(!errors.isEmpty()){
            throw new IllegalArgumentException(errors);
        }
        return this.empresaRepository.save(empresa);
    }

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
