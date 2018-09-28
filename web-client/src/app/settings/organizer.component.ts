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

  teams: Team[] = [];
  users: User[] = [];
  assignedUsers: User[] = [];
  orphanUsers: User[] = [];
  editingId: string;
  showAssigned: boolean;
  showUnassigned: boolean;

  ngOnInit(): void {
    this.update();
    this.noEditing();
  }

  async update() {
    this.teamsService.getTeams().then(teams => this.teams = teams);
    console.log('assigned', this.showAssigned);
    console.log('unassigned', this.showUnassigned);
    let userPromise, orphanPromise;
    if (this.showAssigned) {
      userPromise = this.usersService.getUsers().then(users => this.assignedUsers = users.slice(0, 10));
      await userPromise;
    } else {
      this.assignedUsers = [];
    }
    if (this.showUnassigned) {
      orphanPromise = this.usersService.getUnassignedUsers().then(users => this.orphanUsers = users.slice(0, 10));
      await orphanPromise;
    } else {
      this.orphanUsers = [];
    }
    this.users = this.assignedUsers.concat(this.orphanUsers);
    console.log('users', this.users);
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
