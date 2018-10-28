import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Code} from "./Code";
import {getStoredServerUrl} from "../settings/Settings";


@Injectable()
export class FooterService {

  private codeUrl = '/code';

  constructor(private http: HttpClient) {
  }

  getCode(): Promise<Code> {
    return this.http.get(getStoredServerUrl() + this.codeUrl)
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
