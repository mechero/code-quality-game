import { Injectable } from '@angular/core';
import {Team} from "./Team";
import {TEAMS} from "./mock-teams";

@Injectable()
export class TeamsService {
    getTeams(): Promise<Team[]> {
        return Promise.resolve(TEAMS);
    } 
    getTeam(id: number): Promise<Team> {
        return this.getTeams().then(teams => teams.find(team => team.id === id));
    }
}