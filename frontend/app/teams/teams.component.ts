import {Component, OnInit} from '@angular/core';
import {TeamsService} from './teams.service';
import {StatsRow} from '../common/StatsRow';

@Component({
    moduleId: module.id,
    selector: 'teams',
    templateUrl: 'teams.component.html'
})
export class TeamsComponent implements OnInit {

    constructor(private teamsService: TeamsService) {}

    teams: StatsRow[];

    ngOnInit(): void {
        this.teamsService.getTeams().then(teams => this.teams = teams);
    }

}