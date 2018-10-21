import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {User} from './Member';
import {MemberService} from './member.service';
import {StatsRow} from '../common/StatsRow';

@Component({
  moduleId: module.id,
  selector: 'members',
  templateUrl: 'members.component.html'
})
export class MembersComponent implements OnInit {
  ngOnInit(): void {
    setInterval(() => this.getMembers(), 20 * 1000);
    this.getMembers();
  }

  memberStats: StatsRow[];
  selectedMember: User;

  constructor(private memberService: MemberService) {
  }

  onSelect(member: User): void {
    this.selectedMember = member;
  }

  getMembers(): void {
    console.log('get members');
    this.memberService.getMemberStats().then(memberStats => this.memberStats = memberStats);
  }

}

