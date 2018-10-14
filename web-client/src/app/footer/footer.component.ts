import {Component, OnInit} from '@angular/core';
import {FooterService} from "./footer.service";
import {Code} from "./Code";

@Component({
  moduleId: module.id,
  selector: 'footer',
  templateUrl: 'footer.component.html'
})
export class FooterComponent implements OnInit {
  ngOnInit(): void {
    this.getCode();
  }

  code: Code = new Code('-NOCODE-');

  constructor(private footerService: FooterService) {
  }

  getCode(): void {
    this.footerService.getCode().then(code => this.code = code);
  }

}

