import { Routes } from '@angular/router';
import {BookComponent} from './book/book.component';
import {UserComponent} from './user/user.component';

export const routes: Routes = [
    { path: 'book', component: BookComponent },
    { path: 'user', component: UserComponent },
    { path: '**', component: UserComponent },
    
];
