import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Member } from './Member';
import { MemberService } from './member.service';
import {StatsRow} from '../common/StatsRow';

@Component({
  moduleId: module.id,
  selector: 'members',
  templateUrl: 'members.component.html'
})

export class MembersComponent implements OnInit{
  ngOnInit():void {
    this.getMembers();
  }
  members: StatsRow[];
  selectedMember: Member;

  constructor(private memberService: MemberService) { }

  onSelect(member: Member): void {
    this.selectedMember = member;
  }

  getMembers(): void {
    this.memberService.getMembers().then(members => this.members = members);
  }

  gotoDetail(): void {
    // this.router.navigate(['/detail', this.selectedMember.id]);
  }
}

