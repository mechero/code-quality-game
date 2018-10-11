import {Component} from '@angular/core';
import {RetrieverService} from "./retriever/retriever.service";

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: 'app.component.html'
})
export class AppComponent {
  title = 'Quboo';

  constructor(private retrieverService: RetrieverService) {
  }

  forceRetrieval() {
    this.retrieverService.forceRetrieval().then(
      msg => console.log(msg)
    );
  }
}
