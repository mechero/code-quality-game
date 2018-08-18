import {Injectable} from '@angular/core';
import {StatsRow} from '../common/StatsRow';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable()
export class TeamsService {

  private teamsUrl = environment.serverUrl + '/stats/teams';

  constructor(private http: HttpClient) {
  }

  getTeams(): Promise<StatsRow[]> {
    return this.http.get(this.teamsUrl)
      .toPromise()
      .then(response => response as StatsRow[])
      .catch(this.handleError);
  }

  // getTeam(id: number): Promise<Team[]> {
  //     return this.getTeams().then(teams => teams.find(team => team.id === id));
  // }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred accessing ' + environment.serverUrl, error);
    if (error instanceof HttpErrorResponse) {
      console.error("Response status: " + error.status + " | Message: " + error.message);
    }
    return Promise.reject(error.message || error);
  }
}
