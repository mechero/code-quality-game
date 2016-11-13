import {Component, OnInit} from '@angular/core';
import {TeamsService} from './teams.service';
import {Team} from './Team';

@Component({
    moduleId: module.id,
    selector: 'teams',
    templateUrl: 'teams.component.html'
})
export class TeamsComponent implements OnInit {

    constructor(private teamsService: TeamsService) {}

    teams: Team[];

    ngOnInit(): void {
        this.teamsService.getTeams().then(teams => this.teams = teams);
    }

}