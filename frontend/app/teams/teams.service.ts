import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import {SERVER_URL} from '../config.component';
import {StatsRow} from '../common/StatsRow';

@Injectable()
export class TeamsService {

    private teamsUrl = SERVER_URL + '/stats/teams';

    constructor(private http: Http) { }

    getTeams(): Promise<StatsRow[]> {
        return this.http.get(this.teamsUrl)
            .toPromise()
            .then(response => response.json() as StatsRow[])
            .catch(this.handleError);
    } 
    // getTeam(id: number): Promise<Team[]> {
    //     return this.getTeams().then(teams => teams.find(team => team.id === id));
    // }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}