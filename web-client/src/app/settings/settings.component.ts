import {Component} from "@angular/core";
import {TeamsService} from "../teams/teams.service";
import {MemberService} from "../members/member.service";
import {MessageResponse} from "../common/MessageResponse";
import {SERVER_URL_KEY} from "./Settings";

@Component({
  moduleId: module.id,
  selector: 'settings',
  templateUrl: 'settings.component.html'
})
export class SettingsComponent {

  constructor(private teamsService: TeamsService,
              private usersService: MemberService) {
  }

  responseMessage: MessageResponse = null;
  removeAllUnassignedText: string = null;
  removeAllStatsText: string = null;

  setMessage(res: MessageResponse): void {
    this.responseMessage = res;
  }

  canRemoveAllUnassigned(): boolean {
    return this.removeAllUnassignedText === 'remove-all-unassigned';
  }

  canRemoveAllStats(): boolean {
    return this.removeAllStatsText === 'remove-all-stats';
  }

  removeAllUnnassignedUsers(): void {
    this.usersService.deleteUnassignedUsers().then(response => this.setMessage(response));
    this.removeAllUnassignedText = null;
  }

  removeAllStats(): void {
    this.usersService.removeAllStats().then(response => this.setMessage(response));
    this.removeAllStatsText = null;
  }

  getCurrentServerUrl(): string {
    return localStorage.getItem(SERVER_URL_KEY);
  }

}



