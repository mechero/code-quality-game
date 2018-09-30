import {Injectable} from '@angular/core';
import {StatsRow} from '../common/StatsRow';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from "../../environments/environment";
import {Team} from "./Team";
import {MessageResponse} from "../common/MessageResponse";

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

  deleteTeam(teamId: string): Promise<MessageResponse> {
    return this.http.delete(this.teamsUrl + '/' + teamId).toPromise()
      .then(response => response as MessageResponse)
      .catch(this.handleError)
  }

  private handleError(error: any): Promise<any> {
    if (error.status === 422) {
      return Promise.resolve(new MessageResponse(error.error.message));
    } else {
      console.error('An error occurred accessing ' + environment.serverUrl, error);
      if (error instanceof HttpErrorResponse) {
        console.error("Response status: " + error.status + " | Message: " + error.message);
      }
      return Promise.reject(error.message || error);
    }
  }
}
