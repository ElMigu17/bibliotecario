import { Routes } from '@angular/router';
import {BookComponent} from './components/book/book.component';
import {UserComponent} from './components/user/user.component';

export const routes: Routes = [
    { path: 'book', component: BookComponent },
    { path: 'user', component: UserComponent },
    { path: '**', component: UserComponent },
    
];
