import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from "../../environments/environment";
import {Code} from "./Code";


@Injectable()
export class FooterService {

  private codeUrl = environment.serverUrl + '/code';

  constructor(private http: HttpClient) {
  }

  getCode(): Promise<Code> {
    return this.http.get(this.codeUrl)
      .toPromise()
      .then(response => response as Code)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred accessing ' + this.codeUrl, error);
    if (error instanceof HttpErrorResponse) {
      console.error("Response status: " + error.status + " | Message: " + error.message);
    }
    return Promise.resolve(new Code("-NOCODE-"));
  }

}
