import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private apiUrl = 'http://localhost:3000/users';

  constructor(private http: HttpClient) { }

  // Method to get all users
  getUsers(): Observable<any[]> {
    let a = this.http.get<any[]>(this.apiUrl);
    return a;
  }
  
  postUser(user: any): Observable<any[]> {
    let a = this.http.post<any[]>(this.apiUrl, user);
    return a;
  }

  deleteUser(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  updateUser(user: any): Observable<any> {
    return this.http.put(this.apiUrl, user);
  }
}
