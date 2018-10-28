import {Injectable} from '@angular/core';
import {StatsRow} from '../common/StatsRow';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {User} from "./User";
import {MessageResponse} from "../common/MessageResponse";
import {getStoredServerUrl} from "../settings/Settings";


@Injectable()
export class MemberService {

  private memberStatsUrl = '/stats/users';
  private usersUrl = '/users';

  constructor(private http: HttpClient) {
  }

  getMemberStats(): Promise<StatsRow[]> {
    return this.http.get(getStoredServerUrl() + this.memberStatsUrl)
      .toPromise()
      .then(response => response as StatsRow[])
      .catch(this.handleError);
  }

  getUsers(): Promise<User[]> {
    return this.http.get(getStoredServerUrl() + this.usersUrl + '?assigned')
      .toPromise()
      .then(response => response as User[])
      .catch(this.handleError);
  }

  getUnassignedUsers(): Promise<User[]> {
    return this.http.get(getStoredServerUrl() + this.usersUrl + '?unassigned')
      .toPromise()
      .then(response => response as User[])
      .catch(this.handleError);
  }

  deleteUnassignedUsers(): Promise<MessageResponse> {
    return this.http.delete(getStoredServerUrl() + this.usersUrl + '?unassigned')
      .toPromise()
      .then(response => response as MessageResponse)
      .catch(this.handleError);
  }

  updateUser(user: User) {
    return this.http.put(getStoredServerUrl() + this.usersUrl + '/' + user.id, user)
      .toPromise()
      .then(response => response as User)
      .catch(this.handleError);
  }

  removeAllStats(): Promise<MessageResponse> {
    return this.http.delete(getStoredServerUrl() + this.memberStatsUrl)
      .toPromise()
      .then(response => response as MessageResponse)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred accessing the server', error);
    if (error instanceof HttpErrorResponse) {
      console.error("Response status: " + error.status + " | Message: " + error.message);
    }
    return Promise.reject(error.message || error);
  }
}
