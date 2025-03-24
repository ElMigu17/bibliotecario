import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private apiUrl = 'http://localhost:3000/users';

  constructor(private http: HttpClient) { }

  // Method to get all users
  getUsers(): Observable<any[]> {
    let a = this.http.get<any[]>(this.apiUrl);
    console.log(a);
    return a;
  }
  
  postUser(user: any): Observable<any[]> {
    console.log(user);
    let a = this.http.post<any[]>(this.apiUrl, user);
    console.log(a);
    return a;
  }
}
