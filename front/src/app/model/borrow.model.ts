import { Book } from "./book.model";

export class Borrow {
    id: number;
    id_user: number;
    name_user: string;

    id_book: number;
    title_book: string;
    author_book: string;

    borrow_date: Date;
    deliver_date: Date;
  
    constructor(id: number, id_user: number, name_user: string, id_book: number, title_book: string, author_book: string, borrow_date: Date, deliver_date: Date) {
      this.id = id;
      this.id_user = id_user;
      this.name_user = name_user;

      this.id_book = id_book;
      this.title_book = title_book;
      this.author_book = author_book;

      this.borrow_date = borrow_date;
      this.deliver_date = deliver_date;
    }
}
