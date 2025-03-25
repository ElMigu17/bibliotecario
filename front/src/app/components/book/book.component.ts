import { Component, OnInit, Inject, PLATFORM_ID, AfterViewInit } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { AllCommunityModule, ColDef, ModuleRegistry } from 'ag-grid-community';
import { AgGridAngular } from 'ag-grid-angular';
import { BookServiceService } from "../../services/book/book-service.service";
import { Book } from '../../model/book.model';
import { TableButtonComponent } from '../table-button/table-button.component';

ModuleRegistry.registerModules([AllCommunityModule]);

@Component({
  selector: 'app-book',
  imports: [ReactiveFormsModule, AgGridAngular, CommonModule ],
  providers: [],
  templateUrl: './book.component.html',
  styleUrl: './book.component.scss'
})
export class BookComponent implements OnInit, AfterViewInit {
  bookForm: FormGroup;
  rowData: Array<Book> = [];
  showModel: Boolean = false;
  isBrowser = false;
  showGrid = false;
  isEditing: boolean | null = false;

  defaultColDef = {
    flex: 1,
    minWidth: 120,
    filter: true,
    suppressSizeToFit: true
  };

  colDefs: ColDef[] = [
    { field: "title", width: 200 },
    { field: "author", width: 200 },
    {
      headerName: "Actions",
      cellRenderer: TableButtonComponent,
      cellRendererParams: {
        onEdit: this.editRow.bind(this),
        onDelete: this.deleteBook.bind(this)
      },
      width: 200
    }
  ];

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private bookServiceService: BookServiceService,
    private fb: FormBuilder
  ) {
    
    this.isBrowser = isPlatformBrowser(platformId);
    this.bookForm = this.fb.group({
      id: [''],
      title: ['', [Validators.required]],
      author: ['', [Validators.required, Validators.pattern('[a-zA-ZÀ-ÿ ]+')]],
    });

  };

  ngOnInit() {
    this.updateBooksTable();
  }

  ngAfterViewInit() {
    if (this.isBrowser) {
      setTimeout(() => { this.showGrid = true }, 100);
    }
  }
  
  updateBooksTable() {
    this.bookServiceService.getBooks().subscribe((books) => {
      this.rowData = books;
    });
  }

  editRow(book: any): void {
    this.bookForm.patchValue({
      id: book.id,
      title: book.title,
      author: book.author
    });
    
    this.isEditing = true;
    this.showModel = true;
  }
  
  onSubmitForm(): void {
    if (this.bookForm.valid) {
      const bookData = this.bookForm.value;
      const observable = this.isEditing 
        ? this.bookServiceService.updateBook( bookData)
        : this.bookServiceService.postBook(bookData);
  
      observable.subscribe({
        next: () => {
          this.showModel = false;
          this.setBlankBookForm();
          this.updateBooksTable();
          this.isEditing = false;
        },
        error: (err) => console.error('Error saving book', err)
      });
    }
  }

  setBlankBookForm() {
    this.bookForm.reset({
      id: '',
      title: '',
      author: '',
    });
  }

  deleteBook(book: any): void {
    if (confirm(`Are you sure you want to delete ${book.title}?`)) {
      this.bookServiceService.deleteBook(book.id).subscribe({
        next: () => {
          this.rowData = this.rowData.filter(u => u.id !== book.id);
        },
        error: (err) => {
          console.error('Failed to delete book', err);
        }
      });
    }
  }


}

