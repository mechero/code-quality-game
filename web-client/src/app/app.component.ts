import {Component, OnInit} from '@angular/core';
import {RetrieverService} from "./retriever/retriever.service";
import {FooterService} from "./footer/footer.service";
import {Code} from "./footer/Code";

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: 'app.component.html'
})
export class AppComponent implements OnInit {
  title = 'Quboo';
  code: Code = new Code('-NOCODE-');

  constructor(private retrieverService: RetrieverService,
              private footerService: FooterService) {
  }

  ngOnInit(): void {
    this.getCode();
  }

  getCode(): void {
    this.footerService.getCode().then(code => this.code = code);
  }

  forceRetrieval() {
    this.retrieverService.forceRetrieval().then(
      msg => console.log(msg)
    );
  }
}
