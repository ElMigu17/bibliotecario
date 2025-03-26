import { Routes } from '@angular/router';
import {BookComponent} from './components/book/book.component';
import {UserComponent} from './components/user/user.component';
import { BorrowComponent } from './components/borrow/borrow.component';

export const routes: Routes = [
    { path: 'borrow', component: BorrowComponent },
    { path: 'book', component: BookComponent },
    { path: 'user', component: UserComponent },
    { path: '**', component: UserComponent },
    
];
