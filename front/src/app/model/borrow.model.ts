import { User } from "./user.model";

export class Book {
    id: number;
    user: User;
    book: Book;
    borrow_date: Date;
    deliver_date: Date;
  
    constructor(id: number, user: User, book: Book, author: string, borrow_date: Date, deliver_date: Date) {
      this.id = id;
      this.user = user;
      this.book = book;
      this.borrow_date = borrow_date;
      this.deliver_date = deliver_date;
    }
}
