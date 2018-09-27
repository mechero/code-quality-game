import {Injectable} from '@angular/core';
import {StatsRow} from '../common/StatsRow';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from "../../environments/environment";
import {Team} from "./Team";

@Injectable()
export class TeamsService {

  private teamStatsUrl = environment.serverUrl + '/stats/teams';
  private teamsUrl = environment.serverUrl + '/teams';

  constructor(private http: HttpClient) {
  }

  getTeamStats(): Promise<StatsRow[]> {
    return this.http.get(this.teamStatsUrl)
      .toPromise()
      .then(response => response as StatsRow[])
      .catch(this.handleError);
  }

  getTeams(): Promise<Team[]> {
    return this.http.get(this.teamsUrl).toPromise()
      .then(response => response as Team[])
      .catch(this.handleError);
  }

  createTeam(teamName): Promise<Team> {
    return this.http.post(this.teamsUrl, new Team(teamName)).toPromise()
      .then(response => response as Team)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred accessing ' + environment.serverUrl, error);
    if (error instanceof HttpErrorResponse) {
      console.error("Response status: " + error.status + " | Message: " + error.message);
    }
    return Promise.reject(error.message || error);
  }
}
