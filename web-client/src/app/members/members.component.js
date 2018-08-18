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
const member_service_1 = require("./member.service");
let MembersComponent = class MembersComponent {
  constructor(memberService) {
    this.memberService = memberService;
  }

  ngOnInit() {
    this.getMembers();
  }

  onSelect(member) {
    this.selectedMember = member;
  }

  getMembers() {
    this.memberService.getMembers().then(members = > this.members = members
  )
    ;
  }

  gotoDetail() {
    // this.router.navigate(['/detail', this.selectedMember.id]);
  }
};
MembersComponent = __decorate([
  core_1.Component({
    moduleId: module.id,
    selector: 'members',
    templateUrl: 'members.component.html'
  }),
  __metadata("design:paramtypes", [member_service_1.MemberService])
], MembersComponent);
exports.MembersComponent = MembersComponent;
//# sourceMappingURL=members.component.js.map
