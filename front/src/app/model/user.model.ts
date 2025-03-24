export class User {
    id: number;
    name: string;
    email: string;
    cpf: string;

    constructor(id: number, name: string, email: string, cpf: string) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.cpf = cpf;
    }
}

