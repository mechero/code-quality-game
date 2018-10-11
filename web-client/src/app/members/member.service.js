"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
  var c = arguments.length,
    r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
  if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
  else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
  return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
  if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", {value: true});
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const config_component_1 = require("../config.component");
require("rxjs/add/operator/toPromise");
let MemberService = class MemberService {
  constructor(http) {
    this.http = http;
    this.membersUrl = config_component_1.SERVER_URL + '/stats/users';
  }

  getMembers() {
    return this.http.get(this.membersUrl)
      .toPromise()
      .then(response = > response.json()
  )
  .
    catch(this.handleError);
  }

  // getMember(id: number): Promise<Member> {
  //     return this.getMembers().then(members => members.find(member => member.id === id));
  // }
  handleError(error) {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
};
MemberService = __decorate([
  core_1.Injectable(),
  __metadata("design:paramtypes", [http_1.Http])
], MemberService);
exports.MemberService = MemberService;
//# sourceMappingURL=member.service.js.map
