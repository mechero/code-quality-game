import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {getStoredServerUrl} from "../settings/Settings";

@Injectable()
export class RetrieverService {

  private forceRetrievalUrl = '/retriever/now';

  constructor(private http: HttpClient) {
  }

  forceRetrieval(): Promise<String> {
    return this.http.post(getStoredServerUrl() + this.forceRetrievalUrl, {})
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    if (error instanceof HttpErrorResponse) {
      console.error("Response status: " + error.status + " | Message: " + error.message);
    }
    return Promise.reject(error.message || error);
  }
}
