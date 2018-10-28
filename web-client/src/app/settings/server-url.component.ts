import {Component, OnInit} from "@angular/core";
import {defaultServerUrl, getStoredServerUrl, isServerUrlStored, saveServerUrl} from "./Settings";
import {ServerUrlService} from "./server-url.service";

@Component({
  moduleId: module.id,
  selector: 'server-url',
  templateUrl: 'server-url.component.html'
})
export class ServerUrlComponent implements OnInit {

  url: string;
  serverUrlService: ServerUrlService;

  constructor(serverUrlService: ServerUrlService) {
    this.url = isServerUrlStored() ? getStoredServerUrl() : defaultServerUrl();
    this.serverUrlService = serverUrlService;
  }

  saveServerUrl(): void {
    saveServerUrl(this.url);
    this.serverUrlService.serverUrlUpdated(this.url);
  }

  hasServerUrl(): boolean {
    return isServerUrlStored();
  }

  ngOnInit(): void {
    if(!this.hasServerUrl()) {
      document.getElementById('showModalButton').click();
    }
  }

}
