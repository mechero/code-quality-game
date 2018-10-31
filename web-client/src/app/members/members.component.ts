import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {User} from './User';
import {MemberService} from './member.service';
import {StatsRow} from '../common/StatsRow';
import {ServerUrlService} from "../settings/server-url.service";

@Component({
  moduleId: module.id,
  selector: 'members',
  templateUrl: 'members.component.html'
})
export class MembersComponent implements OnInit {

  ngOnInit(): void {
    setInterval(() => this.getMembers(), 2 * 60 * 1000);
    this.getMembers();
    this.serverUrlService.change.subscribe(ignore => this.getMembers());
  }

  memberStats: StatsRow[];
  selectedMember: User;

  constructor(private memberService: MemberService, private serverUrlService: ServerUrlService) {
  }

  onSelect(member: User): void {
    this.selectedMember = member;
  }

  getMembers(): void {
    this.memberService.getMemberStats().then(memberStats => this.memberStats = memberStats);
  }

}

