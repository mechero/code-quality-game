import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OnInit } from '@angular/core';
import { Member } from './member';
import { MemberService } from './member.service';

@Component({
  moduleId: module.id,
  selector: 'members',
  templateUrl: 'members.component.html'
})

export class MembersComponent implements OnInit{
  ngOnInit():void {
    this.getMembers();
  }
  members: Member[];
  selectedMember: Member;

  constructor(private memberService: MemberService, private router: Router) { }

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

