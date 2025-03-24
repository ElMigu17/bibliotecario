import { Component, OnInit, Inject, PLATFORM_ID, AfterViewInit } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { AllCommunityModule, ColDef, ModuleRegistry } from 'ag-grid-community';
import { AgGridAngular } from 'ag-grid-angular';
import { UserServiceService } from "../../services/user/user-service.service";
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';

ModuleRegistry.registerModules([AllCommunityModule]);

@Component({
  selector: 'app-user',
  imports: [ReactiveFormsModule, AgGridAngular, CommonModule, NgxMaskDirective],
  providers: [          provideNgxMask(),
],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})
export class UserComponent implements OnInit, AfterViewInit {
  userForm: FormGroup;
  rowData: Array<Object> = [];
  showModel: Boolean = false;
  isBrowser = false;
  showGrid = false;

  defaultColDef = {
    flex: 1,
    minWidth: 120,
    filter: true,
    suppressSizeToFit: true
  };

  colDefs: ColDef[] = [
    { field: "name", width: 200 },
    { field: "email", width: 200 },
    { field: "cpf", width: 200 },
  ];

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private userServiceService: UserServiceService,
    private fb: FormBuilder
  ) {
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', Validators.required],
    });
    this.isBrowser = isPlatformBrowser(platformId);

  };

  ngOnInit() {
    this.updateUsersTable();
  }

  ngAfterViewInit() {
    if (this.isBrowser) {
      setTimeout(() => {this.showGrid = true}, 100);
    }
  }
  updateUsersTable() {
    this.userServiceService.getUsers().subscribe((users) => {
      this.rowData = users;
    });
  }

  onSubmit(): void {
    if (this.userForm.valid) {

      this.userServiceService.postUser(this.userForm.value).subscribe((a) => {
        this.showModel = !this.showModel;
        this.setBlankUseForm();
        this.updateUsersTable();
      });
    }

  }

  setBlankUseForm(){
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', Validators.required],
    });
  }
}

