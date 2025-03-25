import { Component, OnInit, Inject, PLATFORM_ID, AfterViewInit } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { AllCommunityModule, ColDef, ModuleRegistry } from 'ag-grid-community';
import { AgGridAngular } from 'ag-grid-angular';
import { UserServiceService } from "../../services/user/user-service.service";
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';
import { User } from '../../model/user.model';
import { TableButtonComponent } from '../table-button/table-button.component';

ModuleRegistry.registerModules([AllCommunityModule]);

@Component({
  selector: 'app-user',
  imports: [ReactiveFormsModule, AgGridAngular, CommonModule, NgxMaskDirective ],
  providers: [provideNgxMask(),
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})
export class UserComponent implements OnInit, AfterViewInit {
  userForm: FormGroup;
  rowData: Array<User> = [];
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
    { field: "name", width: 200 },
    { field: "email", width: 200 },
    { field: "cpf", width: 200,
      valueFormatter: params => {
        if (!params.value) return '';
        return params.value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
      } },
    {
      headerName: "Actions",
      cellRenderer: TableButtonComponent,
      cellRendererParams: {
        onEdit: this.editRow.bind(this),
        onDelete: this.deleteUser.bind(this)
      },
      width: 200
    }
  ];

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private userServiceService: UserServiceService,
    private fb: FormBuilder
  ) {
    
    this.isBrowser = isPlatformBrowser(platformId);
    this.userForm = this.fb.group({
      id: [''],
      name: ['', [Validators.required, Validators.pattern('[a-zA-ZÀ-ÿ ]+')]],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/), this.validateCPF.bind(this)]],
    });

  };

  ngOnInit() {
    this.updateUsersTable();
  }

  ngAfterViewInit() {
    if (this.isBrowser) {
      setTimeout(() => { this.showGrid = true }, 100);
    }
  }
  
  updateUsersTable() {
    this.userServiceService.getUsers().subscribe((users) => {
      this.rowData = users;
    });
  }

  editRow(user: any): void {
    this.userForm.patchValue({
      id: user.id,
      name: user.name,
      email: user.email,
      cpf: user.cpf
    });
    
    this.isEditing = true;
    this.showModel = true;
  }
  

  // Modify your onSubmit to handle both create and update
  onSubmitForm(): void {
    if (this.userForm.valid) {
      const userData = this.userForm.value;
      const observable = this.isEditing 
        ? this.userServiceService.updateUser( userData)
        : this.userServiceService.postUser(userData);
  
      observable.subscribe({
        next: () => {
          this.showModel = false;
          this.setBlankUserForm();
          this.updateUsersTable();
          this.isEditing = false;
        },
        error: (err) => console.error('Error saving user', err)
      });
    }
  }

  setBlankUserForm() {
    this.userForm.reset({
      id: '',
      name: '',
      email: '',
      cpf: '',
    });
  }

  deleteUser(user: any): void {
    if (confirm(`Are you sure you want to delete ${user.name}?`)) {
      this.userServiceService.deleteUser(user.id).subscribe({
        next: () => {
          this.rowData = this.rowData.filter(u => u.id !== user.id);
        },
        error: (err) => {
          console.error('Failed to delete user', err);
          // Add user feedback here (toast, alert, etc.)
        }
      });
    }
  }

  validateCPF(cpf: FormControl): { [key: string]: any } | null {
    let cpfString = cpf.value;
  
    if (cpfString.length !== 11 ) return { invalidQtdDigits: true };
    if (/^(\d)\1{10}$/.test(cpfString)) return { invalidVerifingDigit: true };

    
    if(!(this.cpfDigitValidator(cpfString, 10) && this.cpfDigitValidator(cpfString, 11))){
      return { invalidVerifingDigit: true };
    }

    return null;
  }

  cpfDigitValidator(cpf: String, posDigitVerifier: number):boolean{
    let sum = 0;
    const qtdVerifiedDigits = posDigitVerifier-1;
    for (let i = 0; i < qtdVerifiedDigits; i++) {
      sum += parseInt(cpf.charAt(i)) * (posDigitVerifier - i);
    }
    let remainder = (sum * 10) % 11;
    if (remainder === 10 || remainder === 11) remainder = 0;
    if (remainder !== parseInt(cpf.charAt(qtdVerifiedDigits))) {
      return false;
    }
    return true;
  }
}

