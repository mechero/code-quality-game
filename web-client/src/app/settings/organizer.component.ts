import {Component, OnInit} from "@angular/core";
import {TeamsService} from "../teams/teams.service";
import {Team} from "../teams/Team";
import {MemberService} from "../members/member.service";
import {User} from "../members/User";

@Component({
  moduleId: module.id,
  selector: 'organizer',
  templateUrl: 'organizer.component.html'
})
export class OrganizerComponent implements OnInit {

  constructor(private teamsService: TeamsService,
              private usersService: MemberService) {
  }

  teams: Team[];
  users: User[];
  editingId: string;

  ngOnInit(): void {
    this.update();
    this.noEditing();
  }

  update(): void {
    this.teamsService.getTeams().then(teams => this.teams = teams);
    this.usersService.getUsers().then(users => this.users = users.slice(0, 10));
  }

  editUser(id: string) {
    this.editingId = id;
  }

  async saveUser(user: User) {
    let userPromise = this.usersService.updateUser(user);
    await userPromise;
    this.noEditing();
  }

  cancel(): void {
    this.noEditing();
    this.update();
  }

  noEditing(): void {
    this.editingId = null;
  }

}
