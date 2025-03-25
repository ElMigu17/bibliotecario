import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookServiceService {
  private apiUrl = 'http://localhost:3000/books';

  constructor(private http: HttpClient) { }

  getBooks(): Observable<any[]> {
    let a = this.http.get<any[]>(this.apiUrl);
    return a;
  }
  
  postBook(book: any): Observable<any[]> {
    let a = this.http.post<any[]>(this.apiUrl, book);
    return a;
  }

  deleteBook(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  updateBook(book: any): Observable<any> {
    return this.http.put(this.apiUrl, book);
  }
}
