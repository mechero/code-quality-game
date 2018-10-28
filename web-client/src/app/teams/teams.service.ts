import {Injectable} from '@angular/core';
import {StatsRow} from '../common/StatsRow';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Team} from "./Team";
import {MessageResponse} from "../common/MessageResponse";
import {getStoredServerUrl} from "../settings/Settings";

@Injectable()
export class TeamsService {

  private teamStatsUrl = '/stats/teams';
  private teamsUrl = '/teams';

  constructor(private http: HttpClient) {
  }

  getTeamStats(): Promise<StatsRow[]> {
    return this.http.get(getStoredServerUrl() + this.teamStatsUrl)
      .toPromise()
      .then(response => response as StatsRow[])
      .catch(this.handleError);
  }

  getTeams(): Promise<Team[]> {
    return this.http.get(getStoredServerUrl() + this.teamsUrl).toPromise()
      .then(response => response as Team[])
      .catch(this.handleError);
  }

  createTeam(teamName): Promise<Team> {
    return this.http.post(getStoredServerUrl() + this.teamsUrl, new Team(teamName)).toPromise()
      .then(response => response as Team)
      .catch(this.handleError);
  }

  deleteTeam(teamId: string): Promise<MessageResponse> {
    return this.http.delete(getStoredServerUrl() + this.teamsUrl + '/' + teamId).toPromise()
      .then(response => response as MessageResponse)
      .catch(this.handleError)
  }

  private handleError(error: any): Promise<any> {
    if (error.status === 422) {
      return Promise.resolve(new MessageResponse(error.error.message, true));
    } else {
      console.error('An error occurred accessing the server', error);
      if (error instanceof HttpErrorResponse) {
        console.error("Response status: " + error.status + " | Message: " + error.message);
      }
      return Promise.reject(error.message || error);
    }
  }
}
