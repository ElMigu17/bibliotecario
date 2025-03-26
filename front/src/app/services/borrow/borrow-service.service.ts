import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BorrowServiceService {
  private apiUrl = 'http://localhost:3000/borrow';

  constructor(private http: HttpClient) { }

  getBorrows(): Observable<any[]> {
    let a = this.http.get<any[]>(this.apiUrl);
    return a;
  }
  
  postBorrow(borrow: any): Observable<any[]> {
    let a = this.http.post<any[]>(this.apiUrl, borrow);
    return a;
  }

  deleteBorrow(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  updateBorrow(borrow: any): Observable<any> {
    let d = new Date(borrow.deliver_date)
    borrow.deliver_date = new Date(d.setTime( d.getTime() + d.getTimezoneOffset()*60*1000 ));
    
    return this.http.put(this.apiUrl, borrow);
  }
}