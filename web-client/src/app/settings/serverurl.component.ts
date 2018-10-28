import {Component, OnInit} from "@angular/core";
import {SERVER_URL_KEY} from "./Settings";

@Component({
  moduleId: module.id,
  selector: 'server-url',
  templateUrl: 'serverurl.component.html'
})
export class ServerUrlComponent implements OnInit {

  url: string;

  constructor() {
    this.url = this.hasServerUrl() ? this.getServerUrl() : this.defaultServerUrl();
  }

  saveServerUrl(): void {
    localStorage.setItem(SERVER_URL_KEY, this.url);
  }

  getServerUrl(): string {
    return localStorage.getItem(SERVER_URL_KEY);
  }

  hasServerUrl(): boolean {
    return this.getServerUrl() !== null;
  }

  defaultServerUrl(): string {
    return window.location.protocol + '//' + window.location.hostname + ':8080';
  }


  ngOnInit(): void {
    if(!this.hasServerUrl()) {
      document.getElementById('showModalButton').click();
    }
  }

}
