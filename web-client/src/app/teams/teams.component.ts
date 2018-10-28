import {Component, OnInit} from '@angular/core';
import {TeamsService} from './teams.service';
import {StatsRow} from '../common/StatsRow';
import {ServerUrlService} from "../settings/server-url.service";

@Component({
  moduleId: module.id,
  selector: 'teams',
  templateUrl: 'teams.component.html'
})
export class TeamsComponent implements OnInit {

  constructor(private teamsService: TeamsService, private serverUrlService: ServerUrlService) {
  }

  teams: StatsRow[];

  ngOnInit(): void {
    setInterval(() => this.getTeams(), 2 * 60 * 1000);
    this.getTeams();
    this.serverUrlService.change.subscribe(ignore => this.getTeams());
  }

  getTeams(): void {
    this.teamsService.getTeamStats().then(teams => this.teams = teams);
  }

}
