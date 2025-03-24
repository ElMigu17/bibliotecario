import { Component, OnInit, signal } from '@angular/core';
import type { ColDef } from "ag-grid-community";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { AllCommunityModule, ModuleRegistry } from "ag-grid-community";
import { UserServiceService } from "../../services/user/user-service.service"
import { ReactiveFormsModule } from '@angular/forms';
import { AgGridAngular } from 'ag-grid-angular'; // Angular Data Grid Component
import { CommonModule } from '@angular/common';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';

ModuleRegistry.registerModules([AllCommunityModule]);

@Component({
  selector: 'app-user',
  imports: [ReactiveFormsModule, AgGridAngular, CommonModule, NgxMaskDirective, NgxMaskPipe],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})
export class UserComponent implements OnInit {
  userForm: FormGroup;
  rowData: Array<Object> = [];
  showModel: Boolean = false;

  constructor(
    private userServiceService: UserServiceService,
    private fb: FormBuilder
  ) {

    this.userForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', Validators.required],
    });
  };

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

  ngOnInit() {
    this.updateUsersTable();
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
        this.userForm = this.fb.group({
          name: ['', Validators.required],
          email: ['', [Validators.required, Validators.email]],
          cpf: ['', Validators.required],
        });
        this.updateUsersTable();
      });
    }

  }
}

