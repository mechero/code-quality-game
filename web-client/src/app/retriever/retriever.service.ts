import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable()
export class RetrieverService {

  private forceRetrievalUrl = environment.serverUrl + '/retriever/now';

  constructor(private http: HttpClient) {
  }

  forceRetrieval(): Promise<String> {
    return this.http.post(this.forceRetrievalUrl, null)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred accessing ' + this.forceRetrievalUrl, error);
    if (error instanceof HttpErrorResponse) {
      console.error("Response status: " + error.status + " | Message: " + error.message);
    }
    return Promise.reject(error.message || error);
  }
}
