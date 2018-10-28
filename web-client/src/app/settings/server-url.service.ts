import {EventEmitter, Injectable, Output} from "@angular/core";

@Injectable()
export class ServerUrlService {

  @Output()
  change: EventEmitter<string> = new EventEmitter();

  serverUrlUpdated(url: string) {
    // the url is not used, it's taken from memory instead
    this.change.emit(url);
  }
}
