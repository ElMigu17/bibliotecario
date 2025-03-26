import { Component, OnInit, Inject, PLATFORM_ID, AfterViewInit } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { AllCommunityModule, ColDef, ModuleRegistry } from 'ag-grid-community';
import { AgGridAngular } from 'ag-grid-angular';
import { Borrow } from '../../model/borrow.model';
import { TableButtonComponent } from '../table-button/table-button.component';
import { BorrowServiceService } from '../../services/borrow/borrow-service.service';
import { User } from '../../model/user.model';
import { UserServiceService } from '../../services/user/user-service.service';
import { BookServiceService } from '../../services/book/book-service.service';
import { Book } from '../../model/book.model';

ModuleRegistry.registerModules([AllCommunityModule]);

@Component({
  selector: 'app-borrow',
  imports: [ReactiveFormsModule, AgGridAngular, CommonModule ],
  providers: [],
  templateUrl: './borrow.component.html',
  styleUrl: './borrow.component.scss'
})
export class BorrowComponent implements OnInit, AfterViewInit {
  borrowForm: FormGroup;
  rowData: Array<Borrow> = [];
  showModel: Boolean = false;
  isBrowser = false;
  showGrid = false;
  isEditing: boolean | null = false;
  
  users : Array<User> = [];
  books : Array<Book> = [];

  defaultColDef = {
    flex: 1,
    minWidth: 120,
    filter: true,
    suppressSizeToFit: true
  };

  colDefs: ColDef[] = [
    { headerName: "Nome do usuário", field: "user_name", width: 200 },
    { headerName: "Título do livro", field: "book_title", width: 200 },
    { headerName: "Data do emprestimo", field: "borrow_date", width: 200,
      valueFormatter: function (params) {
        return params.value == null ? "" : new Date(params.value).toLocaleDateString("br");
      },
    },
    { headerName: "Data de entrega", field: "deliver_date", width: 200,
      valueFormatter: function (params) {
        return params.value == null ? "" : new Date(params.value).toLocaleDateString("br");
      },
    },
    { headerName: "Foi entregue", field: "delivered", width: 200, 
    },
    {
      headerName: "Actions",
      cellRenderer: TableButtonComponent,
      cellRendererParams: {
        onEdit: this.editRow.bind(this),
        onDelete: this.deleteBorrow.bind(this)
      },
      width: 200
    }
  ];

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private borrowServiceService: BorrowServiceService,
    private userServiceService: UserServiceService,
    private bookServiceService: BookServiceService,
    private fb: FormBuilder
  ) {
    
    this.isBrowser = isPlatformBrowser(platformId);
    this.borrowForm = this.fb.group({
      id: [''],
      user_id: ['', [Validators.required]],
      book_id: ['', [Validators.required]],
      deliver_date: ['', [Validators.required]],
      borrow_date: [''],
      delivered: [''],
    });

  };

  ngOnInit() {
    this.updateBorrowsTable();
    
    this.userServiceService.getUsers().subscribe((users) => {
      this.users = users;
    });
    this.bookServiceService.getBooks().subscribe((books) => {
      this.books = books;
    });
  }

  ngAfterViewInit() {
    if (this.isBrowser) {
      setTimeout(() => { this.showGrid = true }, 100);
    }
  }
  
  updateBorrowsTable() {
    this.borrowServiceService.getBorrows().subscribe((borrows) => {
      this.rowData = borrows;
    });
  }

  editRow(borrow: any): void {
    
    this.borrowForm.patchValue({
      id: borrow.id,
      user_id: borrow.user_id,
      book_id: borrow.book_id,
      deliver_date: this.formatDateZeros(borrow.deliver_date),
      borrow_date: borrow.borrow_date,
      delivered: borrow.delivered

    });
    console.log(this.borrowForm);
    
    this.isEditing = true;
    this.showModel = true;
  }
  
  onSubmitForm(): void {
    console.log(this.borrowForm)
    if (this.borrowForm.valid) {
      const borrowData = this.borrowForm.value;
      const observable = this.isEditing 
        ? this.borrowServiceService.updateBorrow( borrowData)
        : this.borrowServiceService.postBorrow(borrowData);
  
      observable.subscribe({
        next: () => {
          this.showModel = false;
          this.setBlankBorrowForm();
          this.updateBorrowsTable();
          this.isEditing = false;
        },
        error: (err) => console.error('Error saving borrow', err)
      });
    }
  }

  setBlankBorrowForm() {
    this.borrowForm.reset({
      id: '',
      user_id: '',
      book_id: '',
      deliver_date: '',
      borrow_date: '',
      delivered: ''
    });
  }

  deleteBorrow(borrow: any): void {
    if (confirm(`Are you sure you want to delete ${borrow.title}?`)) {
      this.borrowServiceService.deleteBorrow(borrow.id).subscribe({
        next: () => {
          this.rowData = this.rowData.filter(u => u.id !== borrow.id);
        },
        error: (err) => {
          console.error('Failed to delete borrow', err);
        }
      });
    }
  }

  formatDateZeros(date: string) : string{
    
    const dateObject = new Date(date);    
    let month = dateObject.getMonth() + 1;
    return dateObject.getFullYear() + "-"
      + (month <= 9 ? "0" + month : month) + "-"
      + (dateObject.getDate() <= 9 ? "0" + dateObject.getDate() : dateObject.getDate());
  }
}

