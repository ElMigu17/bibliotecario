import { Component, OnInit } from '@angular/core';
import type { ColDef } from "ag-grid-community";
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

import { AllCommunityModule, ModuleRegistry } from "ag-grid-community";
import {UserServiceService} from "../../services/user/user-service.service"
import { ReactiveFormsModule } from '@angular/forms';
import { User } from '../../model/user.model';

ModuleRegistry.registerModules([AllCommunityModule]);

@Component({
  selector: 'app-user',
  imports: [ReactiveFormsModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})
export class UserComponent implements OnInit {
  userForm: FormGroup;

  constructor(
    private userServiceService: UserServiceService,
    private fb: FormBuilder
  ){
    
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', Validators.required],
    });
  };
  rowData = [
      { make: "Tesla", model: "Model Y", price: 64950, electric: true },
      { make: "Ford", model: "F-Series", price: 33850, electric: false },
      { make: "Toyota", model: "Corolla", price: 29600, electric: false },
  ];


  // Column Definitions: Defines the columns to be displayed.
  colDefs: ColDef[] = [
      { field: "make" },
      { field: "model" },
      { field: "price" },
      { field: "electric" }
  ];


  ngOnInit(){

    console.log("aaaaaaaaaaaaaaaaa")
    this.userServiceService.getUsers().subscribe((a) => {
      console.log(a);
    });
  }

  onSubmit(): void{
    if (this.userForm.valid) {
      console.log(this.userForm.value);

      this.userServiceService.postUser(this.userForm.value).subscribe((a) => {
        console.log(a);
      });
    }
    
  
    console.log()
  }
}

