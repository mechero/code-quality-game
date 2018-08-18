import {Injectable} from '@angular/core';
import {StatsRow} from '../common/StatsRow';
import {SERVER_URL} from '../config.component';
import {HttpClient} from '@angular/common/http';


@Injectable()
export class MemberService {

  private membersUrl = SERVER_URL + '/stats/users';

  constructor(private http: HttpClient) {
  }

  getMembers(): Promise<StatsRow[]> {
    return this.http.get(this.membersUrl)
      .toPromise()
      .then(response => response as StatsRow[])
      .catch(this.handleError);
  }

  // getMember(id: number): Promise<Member> {
  //     return this.getMembers().then(members => members.find(member => member.id === id));
  // }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
