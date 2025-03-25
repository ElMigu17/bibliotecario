

import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';

@Component({
  selector: 'app-grid-action-buttons',
  templateUrl: './table-button.component.html',
  styleUrl: './table-button.component.scss'
})
export class TableButtonComponent implements ICellRendererAngularComp {
  params: any;

  agInit(params: any): void {
    this.params = params;
  }

  refresh(params: any): boolean {
    return false;
  }

  onEditClick($event: any): void {
    $event.stopPropagation();
    if (this.params.onEdit instanceof Function) {
      this.params.onEdit(this.params.node.data);
    }
  }

  onDeleteClick($event: any): void {
    $event.stopPropagation();
    if (this.params.onDelete instanceof Function) {
      this.params.onDelete(this.params.node.data);
    }
  }
}