import {Injectable} from '@angular/core';
import {StatsRow} from '../common/StatsRow';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from "../../environments/environment";
import {User} from "./User";


@Injectable()
export class MemberService {

  private memberStatsUrl = environment.serverUrl + '/stats/users';
  private usersUrl = environment.serverUrl + '/users';

  constructor(private http: HttpClient) {
  }

  getMemberStats(): Promise<StatsRow[]> {
    return this.http.get(this.memberStatsUrl)
      .toPromise()
      .then(response => response as StatsRow[])
      .catch(this.handleError);
  }

  getUsers(): Promise<User[]> {
    return this.http.get(this.usersUrl)
      .toPromise()
      .then(response => response as User[])
      .catch(this.handleError);
  }

  updateUser(user: User) {
    return this.http.put(this.usersUrl + '/' + user.id, user)
      .toPromise()
      .then(response => response as User)
      .catch(this.handleError);
  }

  // getMember(id: number): Promise<Member> {
  //     return this.getMembers().then(members => members.find(member => member.id === id));
  // }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred accessing ' + environment.serverUrl, error);
    if (error instanceof HttpErrorResponse) {
      console.error("Response status: " + error.status + " | Message: " + error.message);
    }
    return Promise.reject(error.message || error);
  }
}
