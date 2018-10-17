export class User {

  constructor(id: string, login: string, alias: string, team: string) {
    this.id = id;
    this.login = login;
    this.alias = alias;
    this.team = team;
  }

  id: string;
  login: string;
  alias: string;
  team: string;
}
